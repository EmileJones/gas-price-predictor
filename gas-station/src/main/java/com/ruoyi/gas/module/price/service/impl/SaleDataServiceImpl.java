package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.service.IGasStationInfoService;
import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.vo.OilSaleDataVO;
import com.ruoyi.gas.module.price.mapper.OilSaleDataMapper;
import com.ruoyi.gas.module.price.service.ISaleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Future;

@Service
public class SaleDataServiceImpl implements ISaleDataService {
    @Autowired
    OilSaleDataMapper saleDataMapper;
    @Autowired
    IGasStationInfoService gasStationInfoService;

    @Override
    public List<OilSaleDataVO> getHistorySaleDataByUserId(Long userId, String stationId, Integer pageNum, Integer pageSize) {
        Long startIndex = (long) ((pageNum - 1) * pageSize);
        List<OilSaleData> oilSaleData = saleDataMapper.selectHistorySaleData(userId, stationId, startIndex, pageSize);
        List<OilSaleDataVO> oilSaleDataVOS = convertOilSaleDataList2OilSaleDataVOList(oilSaleData);
        // 根据id查询加油站名称
        oilSaleDataVOS.stream().forEach(temp -> {
            GasStationInfo gasStationInfo = gasStationInfoService.selectGasStationInfoById(temp.getGasStationId());
            temp.setGasStationName(gasStationInfo.getName());
        });
        return oilSaleDataVOS;
    }

    @Override
    public long selectHistorySaleDataAmount(Long userId) {
        return saleDataMapper.selectHistorySaleDataAmount(userId);
    }

    @Override
    @Async
    public Future<Set<String>> addOilSaleDatas(List<OilSaleData> oilSaleDatas) {
        if (oilSaleDatas == null || oilSaleDatas.size() == 0) {
            return new AsyncResult<>(new HashSet<>());
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
        return new AsyncResult<>(stationIdSet);
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

    private List<OilSaleDataVO> convertOilSaleDataList2OilSaleDataVOList(List<OilSaleData> data) {
        LinkedList<OilSaleDataVO> oilSaleDataVOS = new LinkedList<>();
        for (OilSaleData oilSaleData : data) {
            oilSaleDataVOS.add(convertOilSaleData2OilSaleDataVO(oilSaleData));
        }
        return oilSaleDataVOS;
    }

    private OilSaleDataVO convertOilSaleData2OilSaleDataVO(OilSaleData source) {
        OilSaleDataVO data = new OilSaleDataVO();
        data.setId(source.getId());
        data.setUserId(source.getUserId());
        data.setGasStationId(source.getGasStationId());
        data.setOilType(source.getOilType().getTypeName());
        data.setlNumber(source.getLNumber());
        data.setKgNumber(source.getKgNumber());
        data.setPrice(source.getPrice());
        data.setDate(source.getDate().toString("yyyy年MM月dd日"));
        data.setBatch(source.getBatch());
        return data;
    }
}
