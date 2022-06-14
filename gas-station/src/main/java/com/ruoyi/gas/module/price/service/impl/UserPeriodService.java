package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gas.framewrok.Observer;
import com.ruoyi.gas.module.geo.domain.GasStationUserOwned;
import com.ruoyi.gas.module.geo.mapper.GasStationUserOwnedMapper;
import com.ruoyi.gas.module.price.domain.OpponentPrice;
import com.ruoyi.gas.module.price.domain.UserPeriod;
import com.ruoyi.gas.module.price.domain.form.PeriodForm;
import com.ruoyi.gas.module.price.domain.vo.PeriodVO;
import com.ruoyi.gas.module.price.framework.AddPeriodEventSource;
import com.ruoyi.gas.module.price.mapper.OpponentPriceMapper;
import com.ruoyi.gas.module.price.mapper.UserPeriodMapper;
import com.ruoyi.gas.module.price.service.IPeriodService;
import com.ruoyi.gas.module.price.service.IUserPeriodService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPeriodService implements IUserPeriodService, Observer<AddPeriodEventSource>, ApplicationContextAware {
    @Autowired
    private UserPeriodMapper userPeriodMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private GasStationUserOwnedMapper gasStationUserOwnedMapper;
    @Autowired
    private IPeriodService periodService;
    @Autowired
    private OpponentPriceMapper opponentPriceMapper;
    private ApplicationContext applicationContext;

    @PostConstruct
    void init() {
        PeriodServiceImpl periodService = applicationContext.getBean(PeriodServiceImpl.class);
        periodService.addObserver(this);
    }

    @Override
    public List<UserPeriod> getUserPeriods(Long userId, String gasStationId, Long startIndex, Long amount) {
        UserPeriod condition = new UserPeriod();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        return userPeriodMapper.selectUserPeriod(condition, true, UserPeriodMapper.TIME_STAMP, startIndex, amount);
    }

    @Override
    public long countUserPeriod(Long userId, String gasStationId) {
        return userPeriodMapper.countUserPeriod(userId, gasStationId);
    }


    @Override
    public int addUserPeriods(List<UserPeriod> userPeriods) {
        userPeriods = userPeriods.stream().peek(userPeriod -> {
            Date timeStamp = userPeriod.getTimeStamp();
            timeStamp.setTime(timeStamp.getTime());
            userPeriod.setIsSystemPeriod(false);
        }).collect(Collectors.toList());
        return userPeriodMapper.insertUserPeriod(userPeriods);
    }

    @Override
    public int deleteUserPeriod(long id) {
        UserPeriod condition = getUserPeriodById(id);
        if (!condition.getIsSystemPeriod()) {
            // 删除对手加油站对应周期的价格数据
            OpponentPrice example = new OpponentPrice();
            example.setUserId(SecurityUtils.getUserId());
            example.setUserPeriodId(id);
            opponentPriceMapper.deleteOpponentPrice(example);

            // 删除用户该周期的价格数据
            return userPeriodMapper.deleteUserPeriod(condition);
        }
        else
            throw new ServiceException("此时间节点为发改委时间节点，不可删除");
    }

    @Override
    public Long getUserPeriodId(Long userId, String gasStationId, Date date) {
        UserPeriod condition = new UserPeriod();
        condition.setUserId(userId);
        condition.setGasStationId(gasStationId);
        condition.setTimeStamp(date);
        List<UserPeriod> userPeriods = userPeriodMapper
                .selectUserPeriod(condition, null, null, null, null);

        return userPeriods == null || userPeriods.size() == 0? null : userPeriods.get(0).getId();
    }

    @Override
    public UserPeriod getUserPeriodById(Long id) {
        UserPeriod condition = new UserPeriod();
        condition.setId(id);
        List<UserPeriod> userPeriods = userPeriodMapper
                .selectUserPeriod(condition, null, null, null, null);

        return userPeriods == null || userPeriods.size() == 0? null : userPeriods.get(0);
    }

    @Override
    public void addUserPeriodForNewUserStation(Long userId, String gasStationId) {
        List<PeriodVO> periodList = periodService.getPeriodList(new PeriodForm());
        if (periodList == null || periodList.size() == 0) {
            return;
        }

        List<UserPeriod> userPeriods = periodList.stream()
                .map(periodVO -> {
                    UserPeriod userPeriod = new UserPeriod();
                    userPeriod.setUserId(userId);
                    userPeriod.setGasStationId(gasStationId);
                    userPeriod.setTimeStamp(periodVO.getStartTime());
                    userPeriod.setIsSystemPeriod(true);
                    return userPeriod;
                }).collect(Collectors.toList());
        userPeriodMapper.insertUserPeriod(userPeriods);
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
                userPeriod.setIsSystemPeriod(true);
                list.add(userPeriod);
            }
        }
        // 将数据写入数据库
        if (list.size() > 0) userPeriodMapper.insertUserPeriod(list);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
