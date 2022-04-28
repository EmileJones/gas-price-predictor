package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.vo.OilSaleDataVO;
import com.ruoyi.gas.module.price.mapper.OilSaleDataMapper;
import com.ruoyi.gas.module.price.service.ISaleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@Service
public class SaleDataServiceImpl implements ISaleDataService {
    @Autowired
    OilSaleDataMapper saleDataMapper;

    @Override
    public List<OilSaleDataVO> getHistorySaleDataByUserId(Long userId, Integer pageNum, Integer pageSize) {
        Long startIndex = (long) ((pageNum - 1) * pageSize);
        List<OilSaleData> oilSaleData = saleDataMapper.selectHistorySaleData(userId, startIndex, pageSize);
        return convertOilSaleDataList2OilSaleDataVOList(oilSaleData);
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
}
