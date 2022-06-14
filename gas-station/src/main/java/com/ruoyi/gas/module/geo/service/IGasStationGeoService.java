package com.ruoyi.gas.module.geo.service;

import java.util.List;

import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.GasStationGeo;

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
public interface IGasStationGeoService {
    /**
     * 默认搜索半径
     */
    Integer DEFAULT_RADIUS = 4000;

    /**
     * 列出所有关于系统内加油站周围的加油站的距离信息
     * <p>不自动重新计算距离，如果数据库数据并没有过期，直接读取数据库内容。<br/>
     * 如果数据库没有关于加油站的距离信息或者信息已经过期，那么，计算距离并保存。</p>
     *
     * @param systemStationId  系统内加油站
     * @param outSystemStation 系统外加油站列表
     * @param radius           搜索半径
     * @return 加油站距离列表
     */
    List<GasStationGeo> listStationDistance(GasStationInfo systemStationId,
                                            List<GasStationInfo> outSystemStation, Integer radius);

    /**
     * 列出所有关于系统内加油站周围的加油站的距离信息
     * <p>参数calculate表示数据库是否需要强制计算，并更新加油站的距离信息数据。<br/>
     * 如果true，那么强制更新；如果为false，不强制更新数据</p>
     *
     * @param systemStation        系统内加油站
     * @param outSystemStationList 系统外加油站列表
     * @param newest               是否需要重新计算保存
     * @return 加油站距离列表
     */
    List<GasStationGeo> listStationDistance(GasStationInfo systemStation,
                                            List<GasStationInfo> outSystemStationList, Integer distance, boolean newest);

}
