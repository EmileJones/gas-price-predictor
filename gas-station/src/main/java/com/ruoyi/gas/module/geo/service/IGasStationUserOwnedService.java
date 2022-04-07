package com.ruoyi.gas.module.geo.service;

import java.util.List;
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
     * 查询用户加油站
     * 
     * @param id 用户加油站主键
     * @return 用户加油站
     */
    public GasStationUserOwned selectGasStationUserOwnedById(Long id);

    /**
     * 查询用户加油站列表
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 用户加油站集合
     */
    public List<GasStationUserOwned> selectGasStationUserOwnedList(GasStationUserOwned gasStationUserOwned);

    /**
     * 新增用户加油站
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 结果
     */
    public int insertGasStationUserOwned(GasStationUserOwned gasStationUserOwned);

    /**
     * 修改用户加油站
     * 
     * @param gasStationUserOwned 用户加油站
     * @return 结果
     */
    public int updateGasStationUserOwned(GasStationUserOwned gasStationUserOwned);

    /**
     * 批量删除用户加油站
     * 
     * @param ids 需要删除的用户加油站主键集合
     * @return 结果
     */
    public int deleteGasStationUserOwnedByIds(Long[] ids);

    /**
     * 删除用户加油站信息
     * 
     * @param id 用户加油站主键
     * @return 结果
     */
    public int deleteGasStationUserOwnedById(Long id);
}
