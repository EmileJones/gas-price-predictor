package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.OilSaleData;

import java.util.List;

public interface ISaleDataService {
    /**
     * 获取某个加油站的某种汽油的历史销售记录
     *
     * @param gasStationId 加油站ID
     * @return 某时间段内的历史销量
     */
    List<OilSaleData> getHistorySaleDataByGasStationId(String gasStationId);

    /**
     * 添加系统内加油站的销售记录 <br>
     * id是数据库自增的，不用填 <br>
     * batch是数据库自增的，不用填
     *
     * @param oilSaleData 销售记录
     */
    int addOilSaleDatas(List<OilSaleData> oilSaleData);

    /**
     * 将最新一批的数据删除
     * @return 删除的数据内容
     */
    List<OilSaleData> rollBackLastBatch();
}
