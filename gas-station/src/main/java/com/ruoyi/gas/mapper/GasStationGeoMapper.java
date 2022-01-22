package com.ruoyi.gas.mapper;

import java.util.List;
import com.ruoyi.gas.domain.GasStationGeo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 加油站地理信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
@Mapper
public interface GasStationGeoMapper 
{
    /**
     * 查询加油站地理信息
     * 
     * @param id 加油站地理信息主键
     * @return 加油站地理信息
     */
    public GasStationGeo selectGasStationGeoById(Long id);

    /**
     * 查询加油站地理信息列表
     * 
     * @param gasStationGeo 加油站地理信息
     * @return 加油站地理信息集合
     */
    public List<GasStationGeo> selectGasStationGeoList(GasStationGeo gasStationGeo);

    /**
     * 新增加油站地理信息
     * 
     * @param gasStationGeo 加油站地理信息
     * @return 结果
     */
    public int insertGasStationGeo(GasStationGeo gasStationGeo);

    /**
     * 修改加油站地理信息
     * 
     * @param gasStationGeo 加油站地理信息
     * @return 结果
     */
    public int updateGasStationGeo(GasStationGeo gasStationGeo);

    /**
     * 删除加油站地理信息
     * 
     * @param id 加油站地理信息主键
     * @return 结果
     */
    public int deleteGasStationGeoById(Long id);

    /**
     * 批量删除加油站地理信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGasStationGeoByIds(Long[] ids);

    /**
     * 根据加油站的站点ID获取地理信息
     *
     * @param systemStationId 系统内加油站ID
     * @param outSystemStationId 系统外加油站ID
     * @return 加油站信息
     */
    GasStationGeo selectGasStationGeoByStationId(@Param("systemStationId") String systemStationId,
                                                 @Param("outSystemStationId") String outSystemStationId);
}
