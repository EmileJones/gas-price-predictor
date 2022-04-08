package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.Period;

import java.util.List;

public interface IPeriodService {
    /**
     * 添加周期信息 <br>
     * startTime为必须字段 <br>
     * endTime为null则代此周期是最新周期，结束时间是当前时间，直到添加下一个周期为止 <br>
     * id是数据库自增的，不用填
     *
     * @param period 周期信息
     */
    int addPeriod(Period period);

    /**
     * 获取历史周期信息（降序排列）
     *
     * @param periodNumber 需要获得的历史数据数量
     * @return 周期信息
     */
    List<Period> getHistoryPeriod(int periodNumber);

    /**
     * 修改周期数据
     * @param period 周期数据
     * @return 修改成功的数目
     */
    int updatePeriod(Period period);
}
