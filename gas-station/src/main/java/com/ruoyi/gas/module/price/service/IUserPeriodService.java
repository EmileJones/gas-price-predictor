package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.UserPeriod;

import java.util.Date;
import java.util.List;

public interface IUserPeriodService {
    /**
     * 获取用户周期
     * @param userId 用户唯一ID
     * @param gasStationId 加油站ID
     * @param startIndex 数据开始下标
     * @param amount 要获得的数量
     * @return 用户周期数据
     */
    List<UserPeriod> getUserPeriods(Long userId, String gasStationId, Long startIndex, Long amount);

    /**
     * 获取用户周期总数
     * @param userId 用户ID
     * @param gasStationId 加油站ID
     * @return 用户周期总数
     */
    long countUserPeriod(Long userId, String gasStationId);

    /**
     * 添加用户周期
     * @param userPeriods 用户周期
     * @return 增加成功的用户周期数
     */
    int addUserPeriods(List<UserPeriod> userPeriods);

    /**
     * 根据id删除用户周期
     * @param id 用户id
     * @return 删除的数据数
     */
    int deleteUserPeriod(long id);

    /**
     * 根据id删除用户周期
     * @param userId 用户id
     * @param gasStationId 用户拥有加油站ID
     * @param date 周期时间
     * @return 用户周期ID
     */
    Long getUserPeriodId(Long userId, String gasStationId, Date date);

    /**
     * 根据用户周期ID获取用户周期
     * @param id 用户周期ID
     * @return 用户周期详细信息
     */
    UserPeriod getUserPeriodById(Long id);

    /**
     * 用户在新增加油站的时候，为此加油站拉取最新的周期数据
     * @param userId 用户ID
     * @param gasStationId 加油站ID
     */
    void addUserPeriodForNewUserStation(Long userId, String gasStationId);
}
