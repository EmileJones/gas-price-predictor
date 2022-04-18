package com.ruoyi.gas.module.price.mapper;

import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;


import java.util.List;

@Mapper
public interface OilSaleDataMapper {

    /**
     * 获取某个加油站的的历史销售记录(id降序)
     *
     * @param gasStationId 加油站ID
     * @return 某时间段内的历史销量
     */
    List<OilSaleData> selectHistorySaleData(String gasStationId);

    /**
     * 获取符合条件的加油站信息
     *
     * @param oilSaleData 需要查询的字段
     * @return 符合条件的结果
     */
    List<OilSaleData> selectSaleData(OilSaleData oilSaleData);

    /**
     * 添加销售数据
     *
     * @param oilSalesData 数据
     * @return 数据改变的数量
     */
    int addSaleData(OilSaleData oilSalesData);

    /**
     * 删除交易数据
     *
     * @return 1 删除成功 <br>
     * 0 删除失败
     */
    int deleteSaleData(int id);

    /**
     * 获取某期间内的某种石油的总销量
     *
     * @param gasStationId 加油站ID
     * @param oilType      石油类型
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return 总销量
     */
    Double selectTotalPrice(@Param("gasStationId") String gasStationId,
                            @Param("oilType") OilType oilType,
                            @Param("startTime") DateTime startTime,
                            @Param("endTime") DateTime endTime);

    /**
     * 获取某期间内的某种石油的总收入
     *
     * @param gasStationId 加油站ID
     * @param oilType      石油类型
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return 总收入
     */
    Double selectTotalSalesVolume(@Param("gasStationId") String gasStationId,
                                  @Param("oilType") OilType oilType,
                                  @Param("startTime") DateTime startTime,
                                  @Param("endTime") DateTime endTime);


    /**
     * 回滚最近一次的提交记录
     * @return 修改成功的数据量
     */
    int rollBackLastBatch();

    /**
     * 获取最新的Batch编号，不管其是否被逻辑上的删除
     * @return 最新的Batch编号
     */
    Integer selectLastBatch();

    /**
     *  获取最新的有效的Batch编号
     * @return 最新的Batch编号
     */
    Integer selectLastEffectiveBatch();

    /**
     * 批量插入数据
     * @param oilSaleDatas
     * @return
     */
    int insertBatch(@Param("data") List<OilSaleData> oilSaleDatas);
}
