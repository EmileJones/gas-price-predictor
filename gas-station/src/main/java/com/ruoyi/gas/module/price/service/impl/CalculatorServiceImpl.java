package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.dto.OpponentGasStationDataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilPrice;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import com.ruoyi.gas.module.price.mapper.OilPriceMapper;
import com.ruoyi.gas.module.price.mapper.OilSaleDataMapper;
import com.ruoyi.gas.module.price.mapper.PricePeriodMapper;
import com.ruoyi.gas.module.price.math.Data;
import com.ruoyi.gas.module.price.math.PriceMath;
import com.ruoyi.gas.module.price.service.ICalculatorService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorServiceImpl implements ICalculatorService {
    @Autowired
    OilSaleDataMapper saleDataMapper;
    @Autowired
    PricePeriodMapper periodMapper;
    @Autowired
    OilPriceMapper priceMapper;
    @Override
    public double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException {
        // 获取对手加油站的信息
        List<OpponentGasStationDataForCalculation> outSystemDatas = data.getOutSystemData();
        // 按照要求计算的周期数获得周期
        List<Period> periods = periodMapper.selectLastPeriod(data.getPeriodNumber());
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
                Period period = periods.get(i);
                OilPrice oilPrice = priceMapper.selectPrice(data.getGasStationId(),
                        outGasStationData.getOpponentGasStationId(),
                        period.getId(),
                        data.getOilType());
                if (oilPrice.getPrice() == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("数据库中没有存加油站[")
                            .append(outGasStationData.getOpponentGasStationId())
                            .append("]在[")
                            .append(period.getStartTime())
                            .append("~")
                            .append(period.getEndTime())
                            .append("]周期的")
                            .append(data.getOilType().getTypeName())
                            .append("估计价格");
                    throw new DataIsNotEnoughException(stringBuilder.toString());
                }
                neededData.setOutMoney(i, j, oilPrice.getPrice());
            }
        }
        neededData.setInPresentMoney(data.getPresentMoney());
        neededData.setInTargetMoney(data.getTargetMoney());
        neededData.setPresentAverageSalesVolume(data.getPresentAverageSalesVolume());
        for (int i = 0; i < data.getPeriodNumber(); i++) {
            Period period = periods.get(i);
            int dValueOfDay = period.getDValueOfDay();
            double totalSalesVolume = saleDataMapper.selectTotalSalesVolume(data.getGasStationId(),
                    data.getOilType(),
                    period.getStartTime(),
                    period.getEndTime() == null ? new DateTime() : period.getEndTime());
            double totalPrice = saleDataMapper.selectTotalPrice(data.getGasStationId(),
                    data.getOilType(),
                    period.getStartTime(),
                    period.getEndTime() == null ? new DateTime() : period.getEndTime());
            neededData.setInAverageSalesVolume(i, totalSalesVolume / dValueOfDay);
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
}
