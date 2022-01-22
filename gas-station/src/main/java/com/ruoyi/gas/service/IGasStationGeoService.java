package com.ruoyi.gas.service;

import java.util.List;
import com.ruoyi.gas.domain.GasStationGeo;
import com.ruoyi.gas.domain.GasStationInfo;
import com.ruoyi.gas.domain.vo.GasStationForm;
import com.ruoyi.gas.domain.vo.GasStationGeoForm;
import com.ruoyi.gas.domain.vo.GasStationVO;

/**
 * 加油站地理信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
public interface IGasStationGeoService 
{
    /**
     * 计算系统内系统外的加油站地理信息
     *
     * @param radius 半径设置
     * @param systemStation 系统内加油站信息
     * @param outSystemStation 系统外加油站信息 */
    void saveGeoInfo(int radius, GasStationInfo systemStation, List<GasStationInfo> outSystemStation);

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
     * 批量删除加油站地理信息
     * 
     * @param ids 需要删除的加油站地理信息主键集合
     * @return 结果
     */
    public int deleteGasStationGeoByIds(Long[] ids);

    /**
     * 删除加油站地理信息信息
     * 
     * @param id 加油站地理信息主键
     * @return 结果
     */
    public int deleteGasStationGeoById(Long id);

    /**
     * 查询候选加油站加油站列表
     *
     * @param gasStation 搜索信息
     * @return 返回前十个搜索结果
     */
    List<GasStationVO> listGasStationCandidateList(GasStationForm gasStation);
}
