package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.form.DataForCalculation;
import com.ruoyi.gas.module.price.domain.form.OutGasStationDataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilPrice;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.domain.GuessPrice;
import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.exception.DataIsExistException;
import com.ruoyi.gas.module.price.mapper.OilPriceMapper;
import com.ruoyi.gas.module.price.mapper.OilSaleDataMapper;
import com.ruoyi.gas.module.price.mapper.PricePeriodMapper;
import com.ruoyi.gas.module.price.math.Data;
import com.ruoyi.gas.module.price.math.PriceMath;
import com.ruoyi.gas.module.price.service.IOilPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OilPriceService implements IOilPriceService {
    @Autowired
    OilPriceMapper priceMapper;
    @Autowired
    OilSaleDataMapper saleDataMapper;
    @Autowired
    PricePeriodMapper periodMapper;

    @Override
    public double getAverageSalesVolume(DataForCalculation data) {
        List<OutGasStationDataForCalculation> outSystemDatas = data.getOutSystemData();
        List<Period> periods = periodMapper.selectLastPeriod(data.getPeriodNumber());
        Data neededData = new Data(outSystemDatas.size(), data.getPeriodNumber());
        for (int i = 0; i < outSystemDatas.size(); i++) {
            OutGasStationDataForCalculation outGasStationData = outSystemDatas.get(i);
            neededData.setChaseMoney(i, outGasStationData.getChaseMoney());
            neededData.setPresentMoney(i, outGasStationData.getPresentMoney());
            neededData.setDistance(i, outGasStationData.getDistance());
            neededData.setRouteFactor(i, outGasStationData.getRouteFactor());
            for (int j = 0; j < data.getPeriodNumber(); j++) {
                OilPrice oilPrice = priceMapper.selectPrice(data.getGasStationId(),
                        outGasStationData.getOutGasStationId(),
                        periods.get(i).getId(),
                        data.getOilType());
                neededData.setOutMoney(i, j, oilPrice.getPrice());
            }
        }
        neededData.setInPresentMoney(data.getPresentMoney());
        neededData.setInTargetMoney(data.getTargetMoney());
        neededData.setPresentAverageSalesVolume(data.getPresentAverageSalesVolume());
        for (int i = 0; i < data.getPeriodNumber(); i++) {
            Period period = periods.get(i);
            int dValueOfDay = period.getDValueOfDay();
            double totalSalesVolume = saleDataMapper.selectTotalSalesVolume(data.getGasStationId(), data.getOilType(), period.getStartTime(), period.getEndTime());
            double totalPrice = saleDataMapper.selectTotalPrice(data.getGasStationId(), data.getOilType(), period.getStartTime(), period.getEndTime());
            neededData.setInAverageSalesVolume(i, totalSalesVolume / dValueOfDay);
            neededData.setInMoney(i, totalPrice / totalPrice);
        }
        PriceMath priceMath = new PriceMath(neededData);
        return priceMath.getAsv();
    }

    @Override
    public List<OilSaleData> getHistorySaleDataByOilType(String gasStationId) {
        List<OilSaleData> oilSaleData = saleDataMapper.selectHistorySaleData(gasStationId);
        return oilSaleData;
    }

    @Override
    public List<Period> getHistoryPeriod(int periodNumber) {
        return periodMapper.selectLastPeriod(periodNumber);
    }

    @Override
    public List<GuessPrice> getHistoryPrice(String gasStationId) {
        return priceMapper.selectHistoryPrice(gasStationId);
    }

    @Override
    public void addPrice(GuessPrice guessPrice) throws DataIsExistException {
        OilPrice test = priceMapper.selectPrice(guessPrice.getGasStationId(), guessPrice.getOutGasStationId(), guessPrice.getPeriodId(), OilType.Oil92);
        if (test != null) {
            Period period = periodMapper.selectPeriod(test.getPeriodId());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("数据库中已存在加油站[");
            stringBuilder.append(test.getOutGasStationId());
            stringBuilder.append("]在[");
            stringBuilder.append(period.getStartTime().toString("yyyy-MM-dd"));
            stringBuilder.append("~");
            stringBuilder.append(period.getEndTime().toString("yyyy-MM-dd"));
            stringBuilder.append("]周期的数据，请先删除此数据后再添加");

            DataIsExistException exception = new DataIsExistException(stringBuilder.toString());
            exception.setGasStationId(test.getGasStationId());
            exception.setOutGasStationId(test.getOutGasStationId());
            exception.setPeriodId(test.getPeriodId());
            throw exception;
        }
        priceMapper.addEmptyPrice(guessPrice.getGasStationId(), guessPrice.getOutGasStationId(), guessPrice.getPeriodId());
        OilType[] values = OilType.values();
        for (int i = 0; i < values.length; i++) {
            OilPrice temp = new OilPrice();
            temp.setGasStationId(guessPrice.getGasStationId());
            temp.setOutGasStationId(guessPrice.getOutGasStationId());
            temp.setPeriodId(guessPrice.getPeriodId());
            temp.setOilType(values[i]);
            temp.setPrice(guessPrice.getPrice(values[i]));
            priceMapper.updatePrice(temp);
        }
    }
}
