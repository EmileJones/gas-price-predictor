package com.ruoyi.gas.module.price.mapper;

import com.ruoyi.gas.module.price.domain.framwork.OilPrice;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OilPriceMapper {
    /**
     * 创建一个空的价格数据   <br>
     * 若要添加价格，则使用此方法后需调用updatePrice(OilPrice oilPrice)方法
     *
     * @param gasStationId    系统内加油站ID
     * @param outGasStationId 系统外加油站ID
     * @param periodId        周期ID
     * @return 1   添加成功    <br>
     * 0   添加失败
     */
    int addEmptyPrice(@Param("gasStationId") String gasStationId,
                      @Param("outGasStationId") String outGasStationId,
                      @Param("period") int periodId);


    /**
     * 获取某个时间段内的价格记录
     *
     * @param gasStationId    系统内加油站ID
     * @param outGasStationId 系统外加油站ID
     * @param periodId        周期ID
     * @param oilType         石油类型
     * @return 某时间内的价格记录
     */
    OilPrice selectPrice(@Param("gasStationId") String gasStationId,
                         @Param("outGasStationId") String outGasStationId,
                         @Param("periodId") int periodId,
                         @Param("oilType") OilType oilType);


    /**
     * 获取此用户的历史估价信息（按periodId降序）
     *
     * @param gasStation 用户加油站ID
     * @return 估价信息
     */
    List<OilPrice> selectHistoryPrice(@Param("gasStation") String gasStation,
                                      @Param("oilType") OilType oilType);

    /**
     * 修改价格信息
     *
     * @param oilPrice 价格
     * @return 1   修改成功    <br>
     * 0    修改失败
     */
    int updatePrice(OilPrice oilPrice);

    /**
     * 删除价格信息
     *
     * @param gasStationId    系统内加油站id
     * @param outGasStationId 系统外加油站id
     * @param periodId        周期id
     * @return 1 删除成功 <br>
     * 0 删除失败
     */
    int deletePrice(@Param("gasStationId") String gasStationId,
                    @Param("outGasStationId") String outGasStationId,
                    @Param("period") int periodId);
}
