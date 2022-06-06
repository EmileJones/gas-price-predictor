package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.UserPeriod;
import org.springframework.stereotype.Service;

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
}
