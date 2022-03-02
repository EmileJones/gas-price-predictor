package com.ruoyi.gas.geo.service;

import java.util.List;

import com.ruoyi.gas.geo.domain.GasStationGeo;
import com.ruoyi.gas.geo.domain.GasStationInfo;
import com.ruoyi.gas.geo.domain.form.GasStationGeoForm;
import com.ruoyi.gas.geo.domain.vo.GasStationGeoVO;

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
    public GasStationInfo selectGasStationById(String id);

    /**
     * 查询加油站信息列表
     *
     * @param GasStation 加油站信息
     * @return 加油站信息集合
     */
    public List<GasStationInfo> selectGasStationList(GasStationInfo GasStation);

    /**
     * 新增加油站信息
     *
     * @param gasStation 加油站
     * @return 受影响行数
     */
    public int insertGasStation(GasStationInfo gasStation);

    /**
     * 修改加油站信息
     *
     * @param GasStation 加油站信息
     * @return 结果
     */
    public int updateGasStation(GasStationInfo GasStation);

    /**
     * 批量删除加油站信息
     *
     * @param ids 需要删除的加油站信息主键集合
     * @return 结果
     */
    public int deleteGasStationByIds(String[] ids);

    /**
     * 删除加油站信息信息
     *
     * @param id 加油站信息主键
     * @return 结果
     */
    public int deleteGasStationById(String id);

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
    public int updateGasStationInfo(GasStationInfo gasStationInfo);

    /**
     * 查询加油站地理信息列表
     *
     * @param gasStationGeo 加油站地理信息
     * @return 加油站
     */
    List<GasStationGeoVO> selectGasStationGeoInfoList(GasStationGeoForm gasStationGeo);
}
