package com.ruoyi.gas.module.price.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.gas.module.price.domain.form.PeriodForm;
import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.domain.vo.PeriodVO;
import com.ruoyi.gas.module.price.framework.AddPeriodEventSource;
import com.ruoyi.gas.module.price.mapper.PricePeriodMapper;
import com.ruoyi.gas.module.price.service.*;
import com.ruoyi.gas.module.price.util.DateUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeriodServiceImpl extends AddPeriodEventSource implements IPeriodService {

    @Autowired
    private PricePeriodMapper periodMapper;

    @Override
    public List<PeriodVO> getPeriodList(PeriodForm form) {
        Date startTime = form.getStartTime();
        Date endTime = form.getEndTime();
        System.out.println("StartTime: " + startTime + ", EndTime: " + endTime);

        return periodMapper.selectPeriodList(startTime, endTime).stream()
                .map(this::convertPeriodEntityToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PeriodVO> getHistoryPeriod(int periodNumber) {
        return periodMapper.selectLastPeriod(periodNumber).stream()
                .map(this::convertPeriodEntityToVO)
                .collect(Collectors.toList());
    }

    @Override
    public PeriodVO getPeriodById(Integer id) {
        Period period = periodMapper.selectPeriod(id);
        return convertPeriodEntityToVO(period);
    }

    @Override
    public int updatePeriod(PeriodForm form) {
        // 删除并添加达到修改的目的
        removePeriod(new Integer[]{ form.getId() });
        return addPeriod(form);
    }

    @Override
    public void removePeriod(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        for (Integer id : ids) {
            Period period = periodMapper.selectPeriod(id);
            Date startTime = DateUtil.toDate(period.getStartTime());
//            Period previousPeriod = periodMapper.selectPreviousPeriod(startTime);
//            if (previousPeriod != null) {
//                previousPeriod.setEndTime(period.getEndTime());
//                periodMapper.updatePeriod(previousPeriod);
//            }
            periodMapper.deletePeriod(id);
        }
    }

    @Override
    public int addPeriod(PeriodForm form) {
        // 1. 设置上一周期的结束时间为当前周期的开始时间
        Period period = convertFormToPeriod(form);
//        Period previousPeriod = periodMapper.selectPreviousPeriod(form.getStartTime());
//        if (previousPeriod != null) {
//            DateTime startTime = toDateTime(form.getStartTime());
//            previousPeriod.setEndTime(startTime);
//            periodMapper.updatePeriod(previousPeriod);
//        }

        // 2. 获取下一周期的开始时间为当前周期的结束时间
//        Period nextPeriod = periodMapper.selectNextPeriod(form.getStartTime());
//        DateTime endTime = null;
//        if (nextPeriod != null) {
//            endTime = nextPeriod.getStartTime();
//        }
//        period.setEndTime(endTime);

        // 3. 将所有的用户周期表中加入周期数据
        setPeriod(period);
        notifyAllObserver();

        try {
            return periodMapper.addPeriod(period);
        } catch (DuplicateKeyException exception) {
            throw new ServiceException("请勿提交重复的周期");
        }
    }


    private Period convertFormToPeriod(PeriodForm form) {
        Period period = new Period();
        period.setId(form.getId());
        DateTime startTime = DateUtil.toDateTime(form.getStartTime());
        period.setStartTime(startTime);

        DateTime endTime = DateUtil.toDateTime(form.getEndTime());
//        period.setEndTime(endTime);
        period.setRise(form.getRise());
        period.setRemark(form.getRemark());
        return period;
    }

}
