package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.domain.form.PeriodForm;
import com.ruoyi.gas.module.price.domain.vo.PeriodVO;
import com.ruoyi.gas.module.price.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * 提供关于 大周期信息 的类
 */
public interface IPeriodService {
    /**
     * 添加周期信息 <br>
     * startTime为必须字段 <br>
     * endTime为null则代此周期是最新周期，结束时间是当前时间，直到添加下一个周期为止 <br>
     * id是数据库自增的，不用填
     *
     * @param form 周期信息
     */
    int addPeriod(PeriodForm form);

    /**
     * 获取历史周期信息（降序排列）
     *
     * @param periodNumber 需要获得的历史数据数量
     * @return 周期信息
     */
    List<PeriodVO> getHistoryPeriod(int periodNumber);

    /**
     *
     */
    PeriodVO getPeriodById(Integer id);

    /**
     * 查询所有周期数据
     *
     * @param form 表单条件
     * @return 周期数据
     */
    List<PeriodVO> getPeriodList(PeriodForm form);

    /**
     * 修改周期数据
     *
     * @param period 周期数据
     * @return 修改成功的数目
     */
    int updatePeriod(PeriodForm period);

    /**
     * 删除指定ID的周期
     *
     * @param ids 删除ID列表
     */
    void removePeriod(Integer[] ids);

    default PeriodVO convertPeriodEntityToVO(Period period) {
        PeriodVO vo = new PeriodVO();
        vo.setId(period.getId());
        vo.setRise(period.getRise());
        vo.setRemark(period.getRemark());

        Date startTime = DateUtil.toDate(period.getStartTime());
        Date endTime = DateUtil.toDate(period.getEndTime());
        vo.setStartTime(startTime);
        return vo;
    }

}
