package com.ruoyi.gas.geo.service;

import java.util.List;
import com.ruoyi.gas.geo.domain.GasStationGeo;
import com.ruoyi.gas.geo.domain.GasStationInfo;
import com.ruoyi.gas.geo.domain.form.GasStationForm;
import com.ruoyi.gas.geo.domain.vo.GasStationCandidateVO;

/**
 * 距离服务模块 -- 核心内部模块
 * <p>距离计算服务主要职责：
 * <ol>
 * <li>返回用户所在加油站周围加油站的距离信息【对GeoService负责】</li>
 * <li>保存用户查询到的加油站的距离信息【对自己负责】</li>
 * </ol></p>
 *
 * @author KlenKiven
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
     * 查询候选加油站加油站列表
     *
     * @param gasStation 搜索信息
     * @return 返回前十个搜索结果
     */
    List<GasStationCandidateVO> listGasStationCandidateList(GasStationForm gasStation);
}
