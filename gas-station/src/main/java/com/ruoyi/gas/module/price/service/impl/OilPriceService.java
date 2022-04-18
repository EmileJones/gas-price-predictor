package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.dto.OpponentGasStationDataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilPrice;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import com.ruoyi.gas.module.price.mapper.OilPriceMapper;
import com.ruoyi.gas.module.price.mapper.OilSaleDataMapper;
import com.ruoyi.gas.module.price.mapper.PricePeriodMapper;
import com.ruoyi.gas.module.price.math.Data;
import com.ruoyi.gas.module.price.math.PriceMath;
import com.ruoyi.gas.module.price.service.ICalculatorService;
import com.ruoyi.gas.module.price.service.IOilPriceService;
import com.ruoyi.gas.module.price.service.IPeriodService;
import com.ruoyi.gas.module.price.service.ISaleDataService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OilPriceService implements ICalculatorService, IOilPriceService, ISaleDataService, IPeriodService {
    private static final String GAS_STATION_ID = "gas_station_id";
    private static final String OUT_GAS_STATION_ID = "out_gas_station_id";
    private static final String PERIOD_ID = "period_id";

    @Autowired
    OilPriceMapper priceMapper;
    @Autowired
    OilSaleDataMapper saleDataMapper;
    @Autowired
    PricePeriodMapper periodMapper;


    @Override
    public double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException {
        List<OpponentGasStationDataForCalculation> outSystemDatas = data.getOutSystemData();
        List<Period> periods = periodMapper.selectLastPeriod(data.getPeriodNumber());
        if (periods.size() != data.getPeriodNumber()) {
            throw new DataIsNotEnoughException("数据库中存入的周期不足,期望[" + data.getPeriodNumber() + "]个周期实际[" + periods.size() + "]个周期");
        }
        Data neededData = new Data(outSystemDatas.size(), data.getPeriodNumber());
        for (int i = 0; i < outSystemDatas.size(); i++) {
            OpponentGasStationDataForCalculation outGasStationData = outSystemDatas.get(i);
            neededData.setChaseMoney(i, outGasStationData.getChaseMoney());
            neededData.setPresentMoney(i, outGasStationData.getPresentMoney());
            neededData.setDistance(i, outGasStationData.getDistance());
            neededData.setRouteFactor(i, outGasStationData.getRouteFactor());
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
    public List<OilSaleData> getHistorySaleDataByGasStationId(String gasStationId) {
        List<OilSaleData> oilSaleData = saleDataMapper.selectHistorySaleData(gasStationId);
        return oilSaleData;
    }

    @Override
    public List<Period> getHistoryPeriod(int periodNumber) {
        return periodMapper.selectLastPeriod(periodNumber);
    }

    @Override
    public int updatePeriod(Period period) {
        return periodMapper.updatePeriod(period);
    }

    @Override
    public double getTotalPrice(String gasStationId, OilType oilType, DateTime startTime, DateTime endTime) {
        return saleDataMapper.selectTotalPrice(gasStationId, oilType, startTime, endTime);
    }

    @Override
    public double getTotalSalesVolume(String gasStationId, OilType oilType, DateTime startTime, DateTime endTime) {
        return saleDataMapper.selectTotalSalesVolume(gasStationId, oilType, startTime, endTime);
    }

    @Override
    public List<Map<String, String>> getHistoryPrice(String gasStationId) {
        List<Map<String, String>> list = new LinkedList<>();
        OilType[] values = OilType.values();
        // 初始化
        List<OilPrice> oilPrices = priceMapper.selectHistoryPrice(gasStationId, values[0]);
        for (OilPrice oilPrice : oilPrices) {
            Map<String, String> map = new HashMap<>();
            map.put("gas_station_id", oilPrice.getGasStationId());
            map.put("out_gas_station_id", oilPrice.getOutGasStationId());
            map.put("period_id", oilPrice.getPeriodId().toString());
            map.put(values[0].getTypeName(), oilPrice.getPrice().toString());
        }
        // 完善
        for (int i = 1; i < values.length; i++) {
            oilPrices = priceMapper.selectHistoryPrice(gasStationId, values[i]);
            for (int j = 0; j < list.size(); j++) {
                OilPrice oilPrice = oilPrices.get(j);
                list.get(j).put(values[i].getTypeName(), oilPrice.getPrice().toString());
            }
        }
        return list;
    }

    @Override
    public int updatePrice(Map<String, String> map) {
        boolean flag = false;
        String gasStationId = map.get(GAS_STATION_ID);
        String outGasStationId = map.get(OUT_GAS_STATION_ID);
        int periodId = Integer.parseInt(map.get(PERIOD_ID));
        OilType[] values = OilType.values();
        for (int i = 0; i < values.length; i++) {
            if (map.containsKey(values[i].getTypeName())) {
                OilPrice oilPrice = new OilPrice();
                double price = Double.parseDouble(map.get(values[i].getTypeName()));
                oilPrice.setPrice(price);
                oilPrice.setOilType(values[i]);
                oilPrice.setGasStationId(gasStationId);
                oilPrice.setOutGasStationId(outGasStationId);
                priceMapper.updatePrice(oilPrice);
                flag = true;
            }
        }
        return flag ? 1 : 0;
    }

    @Override
    public int addPrice(Map<String, String> map) {
        String gasStationId = map.get(GAS_STATION_ID);
        String outGasStationId = map.get(OUT_GAS_STATION_ID);
        int periodId = Integer.parseInt(map.get(PERIOD_ID));
        int result = priceMapper.addEmptyPrice(gasStationId, outGasStationId, periodId);
        OilType[] values = OilType.values();
        for (int i = 0; i < values.length; i++) {
            OilPrice temp = new OilPrice();
            temp.setGasStationId(gasStationId);
            temp.setOutGasStationId(outGasStationId);
            temp.setPeriodId(periodId);
            temp.setOilType(values[i]);
            temp.setPrice(map.containsKey(values[i].getTypeName()) ? Double.parseDouble(map.get(values[i].getTypeName())) : 0);
            priceMapper.updatePrice(temp);
        }
        return result;
    }

    @Override
    public Set<String> addOilSaleDatas(List<OilSaleData> oilSaleDatas) {
        if (oilSaleDatas == null || oilSaleDatas.size() == 0) {
            return new HashSet<>();
        }
        Integer lastBatch = saleDataMapper.selectLastBatch();
        if (lastBatch == null) {
            lastBatch = 1;
        } else {
            lastBatch += 1;
        }

        Set<String> stationIdSet = new HashSet<>();
        for (OilSaleData oilSaleData : oilSaleDatas) {
            oilSaleData.setBatch(lastBatch);
            saleDataMapper.addSaleData(oilSaleData);
            stationIdSet.add(oilSaleData.getGasStationId());
        }
        return stationIdSet;
    }

    @Override
    public List<OilSaleData> rollBackLastBatch() {
        Integer lastBatch = saleDataMapper.selectLastEffectiveBatch();
        if (lastBatch == null)
            return new ArrayList<>();
        List<OilSaleData> oilSaleData = saleDataMapper.selectSaleData(new OilSaleData() {{
            setBatch(lastBatch);
        }});
        saleDataMapper.rollBackLastBatch();
        return oilSaleData;
    }

    @Override
    public int addPeriod(Period period) {
        Period lastPeriod = periodMapper.selectLastPeriod(1).get(0);
        lastPeriod.setEndTime(period.getStartTime());
        periodMapper.updatePeriod(lastPeriod);
        return periodMapper.addPeriod(period);
    }
}
