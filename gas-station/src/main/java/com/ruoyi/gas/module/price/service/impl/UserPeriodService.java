package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.gas.framewrok.Observer;
import com.ruoyi.gas.module.geo.domain.GasStationUserOwned;
import com.ruoyi.gas.module.geo.mapper.GasStationUserOwnedMapper;
import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.framework.AddPeriodEventSource;
import com.ruoyi.gas.module.price.mapper.UserPeriodMapper;
import com.ruoyi.gas.module.price.service.IUserPeriodService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class UserPeriodService implements IUserPeriodService, Observer<AddPeriodEventSource> {
    @Autowired
    private UserPeriodMapper userPeriodMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private GasStationUserOwnedMapper gasStationUserOwnedMapper;

    @Override
    public List<UserPeriod> getUserPeriods(Long userId, String gasStationId, Long startIndex, Long amount) {
        UserPeriod condition = new UserPeriod();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        return userPeriodMapper.selectUserPeriod(condition, true, UserPeriodMapper.TIME_STAMP, startIndex, amount);
    }

    @Override
    public int addUserPeriods(List<UserPeriod> userPeriods) {
        return userPeriodMapper.insertUserPeriod(userPeriods);
    }

    @Override
    public int deleteUserPeriod(long id) {
        UserPeriod condition = new UserPeriod();
        condition.setId(id);
        return userPeriodMapper.deleteUserPeriod(condition);
    }

    @Override
    public void update(AddPeriodEventSource generator) {
        // 向用户周期表中添加的数据
        List<UserPeriod> list = new LinkedList<>();
        // 获取所有用户
        List<SysUser> sysUsers = sysUserMapper.selectUserList(new SysUser());
        // 遍历每个用户
        for (SysUser sysUser : sysUsers) {
            // 获取次用户所拥有的加油站
            GasStationUserOwned condition = new GasStationUserOwned();
            condition.setUserId(sysUser.getUserId());
            condition.setStatus(1);
            List<GasStationUserOwned> gasStationUserOwneds = gasStationUserOwnedMapper.selectGasStationUserOwnedList(condition);
            // 为此用户的所有加油站添加周期
            for (GasStationUserOwned gasStationUserOwned : gasStationUserOwneds) {
                String stationId = gasStationUserOwned.getStationId();
                // 将数据封装
                UserPeriod userPeriod = new UserPeriod();
                userPeriod.setUserId(sysUser.getUserId());
                userPeriod.setGasStationId(gasStationUserOwned.getStationId());
                userPeriod.setTimeStamp(generator.getPeriod().getStartTime().toDate());
                list.add(userPeriod);
            }
        }
        // 将数据写入数据库
        userPeriodMapper.insertUserPeriod(list);
    }
}
