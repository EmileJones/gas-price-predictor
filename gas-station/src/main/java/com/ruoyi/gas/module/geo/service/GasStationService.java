package com.ruoyi.gas.module.geo.service;

import com.ruoyi.gas.module.geo.domain.form.GasStationAddForm;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;
import com.ruoyi.gas.module.price.domain.excel.SaleDataExcel;

import java.util.List;
import java.util.Map;

/**
 * 加油站服务 -- 用户侧
 *
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public interface GasStationService {

    /**
     * 查询所有的用户拥有的加油站列表
     * <p>这部分功能主要由：加油站-用户子模块完成</p>
     * @param userId 用户的ID
     * @return 用户拥有的加油站列表
     */
    List<UserStationVO> listGasStationByUserId(Long userId);

    /**
     * 增加用户加油站
     * @param stationAddForm 表单数据
     */
    void addUserStation(GasStationAddForm stationAddForm);

    /**
     * 删除加油站
     * @param stationId 用户ID
     */
    void removeStation(String stationId);

    /**
     * 修改加油站的状态
     * <p>如果是禁用状态，那就禁用，如果不是禁用状态，那么根据具体情况修改状态</p>
     * @param stationId 加油站ID
     * @param status 加油站状态
     */
    void changeStationStatus(String stationId, Integer status);

    /**
     * 保存用户的加油站经营数据
     * @param map 经营数据
     */
    void importSaleData(Map<String, List<SaleDataExcel>> map);
}
