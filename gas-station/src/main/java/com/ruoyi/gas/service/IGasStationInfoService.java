package com.ruoyi.gas.service;

import java.util.List;

import com.ruoyi.gas.domain.GasStationGeo;
import com.ruoyi.gas.domain.GasStationInfo;
import com.ruoyi.gas.domain.vo.GasStationGeoForm;
import com.ruoyi.gas.domain.vo.GasStationGeoVO;

/**
 * 加油站信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
public interface IGasStationInfoService 
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
     * 保存或更新加油站信息
     *
     * @param info 加油站信息
     * @return 结果
     */
    public GasStationInfo saveGasStationInfo(GasStationGeoForm info);

    /**
     * 修改加油站信息
     * 
     * @param gasStationInfo 加油站信息
     * @return 结果
     */
    public int updateGasStationInfo(GasStationInfo gasStationInfo);

    /**
     * 批量删除加油站信息
     * 
     * @param ids 需要删除的加油站信息主键集合
     * @return 结果
     */
    public int deleteGasStationInfoByIds(String[] ids);

    /**
     * 删除加油站信息信息
     * 
     * @param id 加油站信息主键
     * @return 结果
     */
    public int deleteGasStationInfoById(String id);

    /**
     * 查询加油站地理信息列表
     *
     * @param gasStationGeo 加油站地理信息
     * @return 加油站
     */
    List<GasStationGeoVO> selectGasStationGeoInfoList(GasStationGeoForm gasStationGeo);
}
