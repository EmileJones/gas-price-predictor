package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.geo.domain.GasStationGeo;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.service.IGasStationGeoService;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.geo.service.IOpponentMessageService;
import com.ruoyi.gas.module.price.domain.CalculationLog;
import com.ruoyi.gas.module.price.domain.OpponentPrice;
import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.dto.OpponentGasStationDataForCalculation;
import com.ruoyi.gas.module.price.domain.vo.OpponentPriceDataVO;
import com.ruoyi.gas.module.price.domain.vo.PriceDataVO;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import com.ruoyi.gas.module.price.mapper.*;
import com.ruoyi.gas.module.price.math.PriceData;
import com.ruoyi.gas.module.price.math.PriceMath;
import com.ruoyi.gas.module.price.service.ICalculatorService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CalculatorServiceImpl implements ICalculatorService {
    @Autowired
    OilSaleDataMapper saleDataMapper;
    @Autowired
    UserPeriodMapper userPeriodMapper;
    @Autowired
    OpponentPriceMapper opponentPriceMapper;
    @Autowired
    IGasStationGeoService gasStationGeoService;
    @Autowired
    IOpponentMessageService opponentMessageService;
    @Autowired
    IGasStationInfoService gasStationInfoService;
    @Autowired
    CalculationLogMapper calculationLogMapper;

    @Override
    public PriceMath getAverageSalesVolume(DataForCalculation data) {
        // 获取对手加油站的信息
        List<OpponentGasStationDataForCalculation> outSystemDatas = data.getOutSystemData();
        // 按照要求计算的周期数获得周期
        List<UserPeriod> periods = getLastUserPeriodByAmount(data.getUserId(), data.getGasStationId(), data.getPeriodNumber());
        if (periods.size() != data.getPeriodNumber()) {
            throw new DataIsNotEnoughException("数据库中存入的周期不足,期望[" + data.getPeriodNumber() + "]个周期实际[" + periods.size() + "]个周期");
        }
        // 准备将数据封装入Data中
        PriceData neededData = new PriceData(outSystemDatas.size(), data.getPeriodNumber());
        // 将所有系统外的加油站数据封装入Data中
        for (int i = 0; i < outSystemDatas.size(); i++) {
            OpponentGasStationDataForCalculation outGasStationData = outSystemDatas.get(i);
            // 封装系统外加油站的 跟随价格
            neededData.setChaseMoney(i, outGasStationData.getChaseMoney());
            // 封装系统外加油站的 目前价格
            neededData.setPresentMoney(i, outGasStationData.getPresentMoney());
            // 封装系统外加油站的 距离
            neededData.setDistance(i, outGasStationData.getDistance());
            // 封装系统外加油站的 距离因子
            neededData.setRouteFactor(i, outGasStationData.getRouteFactor());
            // 封装系统外加油站每个周期的价格
            for (int j = 0; j < data.getPeriodNumber(); j++) {
                UserPeriod userPeriod = periods.get(j);
                OpponentPrice opponentPrice = getOpponentPrice(data.getUserId(),
                        userPeriod.getId(),
                        data.getGasStationId(),
                        outGasStationData.getOpponentGasStationId());
                BigDecimal price = opponentPrice.getPrice(data.getOilType());
                neededData.setOutMoney(i, j, price.doubleValue());
            }
        }
        // 封装系统内加油站 当前价格
        neededData.setInPresentMoney(data.getPresentMoney());
        // 封装系统内加油站 目标价格
        neededData.setInTargetMoney(data.getTargetMoney());
        // 封装系统内加油站 当前平均销量(日均销量)
        neededData.setPresentAverageSalesVolume(data.getPresentAverageSalesVolume());
        for (int i = 0; i < data.getPeriodNumber(); i++) {
            UserPeriod userPeriod = periods.get(i);
            UserPeriod nextUserPeriod = null;
            if (i != data.getPeriodNumber() - 1) {
                nextUserPeriod = periods.get(i + 1);
            } else {
                nextUserPeriod = new UserPeriod() {{
                    setTimeStamp(new Date());
                }};
            }
            DateTime startTime = new DateTime(userPeriod.getTimeStamp());
            DateTime endTime = new DateTime(nextUserPeriod.getTimeStamp());
            Double totalSalesVolume = saleDataMapper.selectTotalSalesVolume(
                    data.getUserId(),
                    data.getGasStationId(),
                    data.getOilType(),
                    startTime,
                    endTime);
            Double totalPrice = saleDataMapper.selectTotalPrice(
                    data.getUserId(),
                    data.getGasStationId(),
                    data.getOilType(),
                    startTime,
                    endTime);
            if (totalSalesVolume == null || totalPrice == null) {
//                throw new DataIsNotEnoughException("数据库中存入的销售数据不足,在[" + startTime.toString("yyyy-MM-dd") + "---" + endTime.toString("yyyy-MM-dd") + "]时间内没有足够的销售数据");
                neededData.setInAverageSalesVolume(i, Double.valueOf(0));
                // 封装系统内加油站 第i期的综合单价
                neededData.setInMoney(i, Double.valueOf(0));
            } else {
                // 当前周期持续时长
                double differenceDay = calculateDifferenceDay(startTime, endTime);
                // 封装系统内加油站 第i期的平均销量
                neededData.setInAverageSalesVolume(i, totalSalesVolume / differenceDay);
                // 封装系统内加油站 第i期的综合单价
                neededData.setInMoney(i, totalPrice / totalPrice);
            }
        }
        // 计算
        PriceMath priceMath = new PriceMath(neededData);
        // 封装日志数据
        CalculationLog log = new CalculationLog();
        log.setGasStationId(data.getGasStationId());
        log.setOilType(data.getOilType());
        log.setUserId(data.getUserId());
        log.setTimeStamp(new Date());
        log.setParam(priceMath.getY());
        // 记录日志
        calculationLogMapper.insertSingle(log);
        return priceMath;
    }

    @Override
    public PriceMath getAverageSalesVolume(Long userId, PriceDataVO priceData) {
        // 开始封装数据 QAQ
        DataForCalculation data = new DataForCalculation();
        // 一、传入用户ID
        data.setUserId(userId);
        // 二、传入加油站ID
        data.setGasStationId(priceData.getStationId());
        // 三、将周期数传入
        data.setPeriodNumber(16);
        // 四、封装对手加油站信息
        // 0.准备一个List用于存储最终的GasStationGeo信息
        List<GasStationGeo> finalGasStationGeos = new LinkedList<>();
        // 1.获取本加油站的对手加油站信息
        List<GasStationInfo> gasStationInfos = gasStationInfoService.listOutSystemStationBySystemStationId(priceData.getStationId());
        // 2.获取本加油站的加油站信息
        GasStationInfo systemGasStationInfo = gasStationInfoService.selectGasStationInfoById(priceData.getStationId());
        // 3.获取对手加油站的地理位置信息
        List<GasStationGeo> gasStationGeos = gasStationGeoService.listStationDistance(systemGasStationInfo, gasStationInfos, IGasStationGeoService.DEFAULT_RADIUS);
        // 4.将用户承认的对手加油站ID存储到Map中，方便将用户不承认的加油站淘汰出计算，以及封装数据
        Map<String, OpponentPriceDataVO> map = new HashMap<>();
        for (OpponentPriceDataVO temp : priceData.getOpponentPriceData()) {
            map.put(temp.getOutSystemGasStationId(), temp);
        }
        // 5.开始筛选数据，筛选出符合条件的数据
        gasStationGeos.stream().forEach(gasStationGeo -> {
            if (map.containsKey(gasStationGeo.getOutSystemStationId())) {
                finalGasStationGeos.add(gasStationGeo);
            }
        });
        // 6.变换数据格式为OpponentGasStationDataForCalculation格式
        List<OpponentGasStationDataForCalculation> opponentMessages = new ArrayList<>();
        for (GasStationGeo temp : finalGasStationGeos) {
            OpponentGasStationDataForCalculation opponentMessage = new OpponentGasStationDataForCalculation();
            // 获取对应的输入数据
            OpponentPriceDataVO tempPriceDataVO = map.get(temp.getOutSystemStationId());
            // 封装 对手加油站ID
            opponentMessage.setOpponentGasStationId(temp.getOutSystemStationId());
            // 封装 跟随价格
            opponentMessage.setChaseMoney(tempPriceDataVO.getChaseMoney());
            // 封装 影响因素
            opponentMessage.setRouteFactor(temp.getRouteFactor());
            // 封装 距离
            opponentMessage.setDistance(temp.getDistance());
            // 封装 当前价格
            opponentMessage.setPresentMoney(tempPriceDataVO.getPresentMoney());
            // 存入列表中
            opponentMessages.add(opponentMessage);
        }
        // 7.封装 对手加油站信息
        data.setOutSystemData(opponentMessages);
        // 五、封装 目标价格
        data.setTargetMoney(priceData.getTargetMoney());
        // 六、封装 当前价格
        data.setPresentMoney(priceData.getPresentMoney());
        // 七、封装 当前平均销量
        data.setPresentAverageSalesVolume(priceData.getPresentAverageSalesVolume());
        // 八、封装石油类型
        data.setOilType(priceData.getOilType());
        return getAverageSalesVolume(data);
    }

    /**
     * 获取最新的几个周期
     *
     * @param userId       用户ID
     * @param gasStationId 系统内加油站ID
     * @param amount       需要获得的周期数量
     * @return 周期数量
     */
    private List<UserPeriod> getLastUserPeriodByAmount(long userId, String gasStationId, int amount) {
        UserPeriod condition = new UserPeriod();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        return userPeriodMapper.selectUserPeriod(condition, true, UserPeriodMapper.TIME_STAMP, 0l, (long) amount);
    }

    /**
     * 获取某个周期的竞争对手的价格
     *
     * @param userId          用户ID
     * @param userPeriodId    周期ID
     * @param gasStationId    系统内加油站ID
     * @param outGasStationId 系统外加油站ID
     * @return 竞争对手价格(若没有输入则返回全为0的数据)
     */
    private OpponentPrice getOpponentPrice(Long userId, Long userPeriodId, String gasStationId, String outGasStationId) {
        OpponentPrice condition = new OpponentPrice();
        condition.setUserPeriodId(userPeriodId);
        condition.setOutGasStationId(outGasStationId);
        List<OpponentPrice> result = opponentPriceMapper.selectOpponentPrices(condition, null, null, null, null);
        if (result.size() == 1) {
            return result.get(0);
        }
        return createOpponentPriceAndSaveInDataBase(userId, userPeriodId, gasStationId, outGasStationId);
    }

    /**
     * 创建一个数据全为0的OpponentPrice,并存入数据库中
     *
     * @param userId          用户ID
     * @param userPeriodId    周期ID
     * @param gasStationId    系统内加油站ID
     * @param outGasStationId 系统外加油站ID
     * @return OpponentPrice对象
     */
    private OpponentPrice createOpponentPriceAndSaveInDataBase(Long userId, Long userPeriodId, String gasStationId, String outGasStationId) {
        OpponentPrice opponentPrice = new OpponentPrice();
        opponentPrice.setUserId(userId);
        opponentPrice.setUserPeriodId(userPeriodId);
        opponentPrice.setGasStationId(gasStationId);
        opponentPrice.setOutGasStationId(outGasStationId);
        opponentPrice.setPrice00(new BigDecimal(0));
        opponentPrice.setPrice92(new BigDecimal(0));
        opponentPrice.setPrice95(new BigDecimal(0));
        opponentPrice.setPrice98(new BigDecimal(0));
        ArrayList<OpponentPrice> save = new ArrayList<>();
        save.add(opponentPrice);
        opponentPriceMapper.addOpponentPrice(save);
        return opponentPrice;
    }

    /**
     * 计算时间差
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 时间差/天
     */
    private double calculateDifferenceDay(DateTime start, DateTime end) {
        long difference = end.getMillis() - start.getMillis();
        return difference / 1000 / 60 / 60 / 24;
    }
}
