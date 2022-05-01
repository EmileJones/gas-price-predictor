package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.mapper.UserPeriodMapper;
import com.ruoyi.gas.module.price.service.IUserPeriodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserPeriodService implements IUserPeriodService {
    @Autowired
    private UserPeriodMapper userPeriodMapper;

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
}
