package com.ruoyi.gas.mapper;

import java.util.List;
import com.ruoyi.gas.domain.GasStationArgument;

/**
 * 加油站关键参数Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-05
 */
public interface GasStationArgumentMapper 
{
    /**
     * 查询加油站关键参数
     * 
     * @param id 加油站关键参数主键
     * @return 加油站关键参数
     */
    public GasStationArgument selectGasStationArgumentById(Long id);

    /**
     * 查询加油站关键参数列表
     * 
     * @param gasStationArgument 加油站关键参数
     * @return 加油站关键参数集合
     */
    public List<GasStationArgument> selectGasStationArgumentList(GasStationArgument gasStationArgument);

    /**
     * 新增加油站关键参数
     * 
     * @param gasStationArgument 加油站关键参数
     * @return 结果
     */
    public int insertGasStationArgument(GasStationArgument gasStationArgument);

    /**
     * 修改加油站关键参数
     * 
     * @param gasStationArgument 加油站关键参数
     * @return 结果
     */
    public int updateGasStationArgument(GasStationArgument gasStationArgument);

    /**
     * 删除加油站关键参数
     * 
     * @param id 加油站关键参数主键
     * @return 结果
     */
    public int deleteGasStationArgumentById(Long id);

    /**
     * 批量删除加油站关键参数
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasStationArgumentByIds(Long[] ids);

    /**
     * 根据加油站参数名检索参数值
     * @param name 参数名
     * @return 参数值
     */
    public GasStationArgument selectGasStationArgumentByName(String name);
}
