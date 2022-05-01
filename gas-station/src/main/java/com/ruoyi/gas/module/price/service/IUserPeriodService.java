package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.UserPeriod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserPeriodService {

    int addUserPeriods(List<UserPeriod> userPeriods);

    int deleteUserPeriod(long id);

}
