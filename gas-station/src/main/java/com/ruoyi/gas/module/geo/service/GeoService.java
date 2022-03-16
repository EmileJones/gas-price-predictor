package com.ruoyi.gas.module.geo.service;

import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationGeoForm;
import com.ruoyi.gas.module.geo.domain.vo.GasStationGeoVO;
import com.ruoyi.gas.module.geo.domain.form.GasStationForm;
import com.ruoyi.gas.module.geo.domain.vo.GasStationCandidateVO;

import java.util.List;

/**
 * 地理信息模块 -- 主模块服务
 * @author KlenKiven
 */
public interface GeoService {

    /**
     * 查询所有的用户拥有的加油站列表
     * <p>这部分功能主要由：加油站信息子模块完成</p>
     * @return 用户拥有的加油站列表
     */
    GasStationInfo listGasStationInfoByUserId();

    /**
     * 查询系统内加油站的距离信息
     * <p>这部分信息主要由：加油站距离计算子模块完成</p>
     * @param geoForm 系统内加油站ID
     * @return 加油站距离信息VO
     */
    List<GasStationGeoVO> listStationGeo(GasStationGeoForm geoForm);

    /**
     * 根据查询条件查找到所有是符合条件的备选加油站信息
     * <p>地图API</p>
     * @param form 查询表单
     * @return 候选加油站列表
     */
    List<GasStationCandidateVO> listCandidateStations(GasStationForm form);
}
