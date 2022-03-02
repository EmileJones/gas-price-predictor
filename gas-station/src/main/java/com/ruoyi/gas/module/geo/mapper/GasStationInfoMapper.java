package com.ruoyi.gas.module.geo.mapper;

import java.util.List;
import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 加油站信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
@Mapper
public interface GasStationInfoMapper 
{
    /**
     * 查询加油站信息
     * 
     * @param id 加油站信息主键
     * @return 加油站信息
     */
    public GasStationInfo selectGasStationInfoById(String id);

    /**
     * 查询加油站信息列表
     * 
     * @param gasStationInfo 加油站信息
     * @return 加油站信息集合
     */
    public List<GasStationInfo> selectGasStationInfoList(GasStationInfo gasStationInfo);

    /**
     * 新增加油站信息
     * 
     * @param gasStationInfo 加油站信息
     * @return 结果
     */
    public int insertGasStationInfo(GasStationInfo gasStationInfo);

    /**
     * 修改加油站信息
     * 
     * @param gasStationInfo 加油站信息
     * @return 结果
     */
    public int updateGasStationInfo(GasStationInfo gasStationInfo);

    /**
     * 删除加油站信息
     * 
     * @param id 加油站信息主键
     * @return 结果
     */
    public int deleteGasStationInfoById(String id);

    /**
     * 批量删除加油站信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasStationInfoByIds(String[] ids);

    /**
     * 根据条件查询当前加油站
     *
     * @param sysPrefix 系统ID前缀
     * @param location 加油站位置
     * @return 加油站信息
     */
    GasStationInfo selectOneByCondition(@Param("sysPrefix") String sysPrefix,
                                        @Param("location") String location);

    /**
     * 用于判断是否存在这条记录
     * 如果存在，那么就返回这个结果。
     * NOTE: 这里保证，找到的结果就是系统外的站点结果
     *
     * @param location 经纬度信息
     * @return 系统内地理信息
     */
    GasStationInfo selectOneByLocation(@Param("location") String location);

    /**
     * 根据ID查询ID最大的一个信息
     *
     * @param prefix ID前缀
     * @return 加油站信息
     */
    GasStationInfo selectOneByIdHighest(@Param("prefix") String prefix);

    /**
     * 根据经纬度得到ID列表
     *
     * @param location 经纬度
     * @return ID列表
     */
    List<String> selectIdByLocation(@Param("location") String location);

}
