package com.ruoyi.gas.module.geo.service;

import java.util.List;

import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.form.GasStationGeoForm;

/**
 * 加油站信息服务模块
 * <p>加油站信息的增删查改<p/>
 * @author KlenKiven
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
     * @param GasStation 加油站信息
     * @return 加油站信息集合
     */
    public List<GasStationInfo> selectGasStationInfoList(GasStationInfo GasStation);

    /**
     * 新增加油站信息
     *
     * @param gasStation 加油站
     * @return 受影响行数
     */
    int insertGasStationInfo(GasStationInfo gasStation);

    /**
     * 修改加油站信息
     *
     * @param GasStation 加油站信息
     * @return 结果
     */
    public int updateGasStationInfo(GasStationInfo GasStation);

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

    //***********************************************************************************************************

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
    public int updateGasStation(GasStationInfo gasStationInfo);

    GasStationInfo getSystemStationByLocation(String location,int radius);

    List<GasStationInfo> listOutSystemStationBySystemStationId(String SystemId);
}
