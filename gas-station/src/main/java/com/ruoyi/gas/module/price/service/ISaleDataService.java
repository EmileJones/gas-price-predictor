package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.vo.OilSaleDataVO;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 提供关于 加油站销售数据 的类
 */
public interface ISaleDataService {
    /**
     * 获取某个用户的历史销售记录
     *
     * @param userId 用户ID
     * @return 某时间段内的历史销量
     */
    List<OilSaleDataVO> getHistorySaleDataByUserId(Long userId, String stationId, Integer pageNum, Integer pageSize);

    /**
     * 获取某个用户的收据的数量
     *
     * @param userId 用户ID
     * @return 收据数量
     */
    long selectHistorySaleDataAmount(Long userId);

    /**
     * 添加系统内加油站的销售记录 <br>
     * id是数据库自增的，不用填 <br>
     * batch是数据库自增的，不用填
     *
     * @param oilSaleData 销售记录
     */
    Future<Set<String>> addOilSaleDatas(List<OilSaleData> oilSaleData);

    /**
     * 将最新一批的数据删除
     *
     * @return 删除的数据内容
     */
    List<OilSaleData> rollBackLastBatch();

}
