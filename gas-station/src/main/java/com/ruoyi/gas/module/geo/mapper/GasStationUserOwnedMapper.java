package com.ruoyi.gas.module.geo.mapper;

import com.ruoyi.gas.module.geo.domain.GasStationUserOwned;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户加油站Mapper接口
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 */
@Mapper
public interface GasStationUserOwnedMapper 
{
    /**
     * 查询用户加油站
     * 
     * @param id 用户加油站主键
     * @return 用户加油站
     */
    public GasStationUserOwned selectGasStationUserOwnedById(Long id);

    /**
     * 查询用户加油站列表
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 用户加油站集合
     */
    public List<GasStationUserOwned> selectGasStationUserOwnedList(GasStationUserOwned gasStationUserOwned);

    /**
     * 新增用户加油站
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 结果
     */
    public int insertGasStationUserOwned(GasStationUserOwned gasStationUserOwned);

    /**
     * 修改用户加油站
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 结果
     */
    public int updateGasStation(GasStationUserOwned gasStationUserOwned);

    /**
     * 批量删除用户加油站
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasStationUserOwnedByIds(Long[] ids);

}
