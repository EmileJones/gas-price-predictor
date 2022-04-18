package com.ruoyi.gas.module.geo.service;

import java.util.List;
import java.util.Set;

import com.ruoyi.gas.module.geo.domain.GasStationInfo;
import com.ruoyi.gas.module.geo.domain.GasStationUserOwned;
import com.ruoyi.gas.module.geo.domain.vo.UserStationVO;

/**
 * 用户加油站Service接口
 * 
 * @author ruoyi
 * @date 2022-04-07
 */
public interface IGasStationUserOwnedService 
{
    /**
     * 根据用户的ID获取用户所拥有的加油站
     * @param userId 用户ID
     * @return 加油站列表
     */
    List<UserStationVO> listUserOwnedStation(Long userId);

    /**
     * 用户创建加油站信息
     * <p>创建用户加油站表信息，保存信息</p>
     * @param station 加油站信息
     */
    void createStationForUser(GasStationInfo station);

    /**
     * 删除用户指定的加油站
     * @param userId userId 用户ID
     * @param stationId 加油站ID
     */
    void deleteGasStation(Long userId, String stationId);

    /**
     * 修改加油站的状态
     *
     * @param userId 用户ID
     * @param stationId 加油站ID
     * @param status 加油站状态
     */
    void changeStationStatus(Long userId, String stationId, Integer status);

    /**
     * 数据导入成功后需要做的事情
     * <p>更新加油站状态</p>
     * @param stationIdSet 导入成功的加油站ID
     */
    void postImportSaleData(Set<String> stationIdSet);
}
