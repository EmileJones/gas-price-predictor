package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.OpponentPrice;
import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.dto.OpponentGasStationDataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilPrice;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import com.ruoyi.gas.module.price.mapper.*;
import com.ruoyi.gas.module.price.math.Data;
import com.ruoyi.gas.module.price.math.PriceMath;
import com.ruoyi.gas.module.price.service.ICalculatorService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CalculatorServiceImpl implements ICalculatorService {
    @Autowired
    OilSaleDataMapper saleDataMapper;
    @Autowired
    UserPeriodMapper userPeriodMapper;
    @Autowired
    OpponentPriceMapper opponentPriceMapper;

    @Override
    public double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException {
        // 获取对手加油站的信息
        List<OpponentGasStationDataForCalculation> outSystemDatas = data.getOutSystemData();
        // 按照要求计算的周期数获得周期
        List<UserPeriod> periods = getLastUserPeriodByAmount(data.getUserId(), data.getGasStationId(), data.getPeriodNumber());
        if (periods.size() != data.getPeriodNumber()) {
            throw new DataIsNotEnoughException("数据库中存入的周期不足,期望[" + data.getPeriodNumber() + "]个周期实际[" + periods.size() + "]个周期");
        }
        // 准备将数据封装入Data中
        Data neededData = new Data(outSystemDatas.size(), data.getPeriodNumber());
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
                OpponentPrice opponentPrice = getOpponentPrice(data.getUserId(), outGasStationData.getOpponentGasStationId());
                BigDecimal price = opponentPrice.getPrice(data.getOilType());
                neededData.setOutMoney(i, j, price.doubleValue());
            }
        }
        // 封装系统内加油站 当前价格
        neededData.setInPresentMoney(data.getPresentMoney());
        // 封装系统内加油站 目标价格
        neededData.setInTargetMoney(data.getTargetMoney());
        // 封装系统内加油站 当前平均销量
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
            double totalSalesVolume = saleDataMapper.selectTotalSalesVolume(data.getGasStationId(),
                    data.getOilType(),
                    startTime,
                    endTime);
            double totalPrice = saleDataMapper.selectTotalPrice(data.getGasStationId(),
                    data.getOilType(),
                    startTime,
                    endTime);
            // 当前周期持续时长
            double differenceDay = calculateDifferenceDay(startTime, endTime);
            // 封装系统内加油站 第i期的平均销量
            neededData.setInAverageSalesVolume(i, totalSalesVolume / differenceDay);
            // 封装系统内加油站 第i期的综合单价
            neededData.setInMoney(i, totalPrice / totalPrice);
        }
        PriceMath priceMath = new PriceMath(neededData);
        return priceMath.getAsv();
    }

    @Override
    public double getTotalPrice(String gasStationId, OilType oilType, DateTime startTime, DateTime endTime) {
        return saleDataMapper.selectTotalPrice(gasStationId, oilType, startTime, endTime);
    }

    @Override
    public double getTotalSalesVolume(String gasStationId, OilType oilType, DateTime startTime, DateTime endTime) {
        return saleDataMapper.selectTotalSalesVolume(gasStationId, oilType, startTime, endTime);
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
     * @param userPeriodId 周期ID
     * @param outStationId 系统外加油站ID
     * @return 竞争对手价格(若没有输入则返回全为0的数据)
     */
    private OpponentPrice getOpponentPrice(Long userPeriodId, String outStationId) {
        OpponentPrice condition = new OpponentPrice();
        condition.setUserPeriodId(userPeriodId);
        condition.setOutGasStationId(outStationId);
        List<OpponentPrice> result = opponentPriceMapper.selectOpponentPrices(condition, null, null, null, null);
        if (result.size() == 1) {
            return result.get(0);
        }
        return createOpponentPriceAndSaveInDataBase(userPeriodId, outStationId);
    }

    /**
     * 创建一个数据全为0的OpponentPrice,并存入数据库中
     * @param userPeriodId 用户周期ID
     * @param outGasStationId 系统外加油站ID
     * @return OpponentPrice对象
     */
    private OpponentPrice createOpponentPriceAndSaveInDataBase(Long userPeriodId, String outGasStationId) {
        OpponentPrice opponentPrice = new OpponentPrice();
        opponentPrice.setUserPeriodId(userPeriodId);
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
     * @param start 开始时间
     * @param end 结束时间
     * @return 时间差/天
     */
    private double calculateDifferenceDay(DateTime start, DateTime end) {
        long difference = end.getMillis() - start.getMillis();
        return difference / 1000 / 60 / 60 / 24;
    }
}
