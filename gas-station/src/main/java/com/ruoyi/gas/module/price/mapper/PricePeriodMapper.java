package com.ruoyi.gas.module.price.mapper;

import com.ruoyi.gas.module.price.domain.Period;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PricePeriodMapper {
    /**
     * 获取最近 periodNumber 个周期的数据
     * @param periodNumber 需要获取最近几次的数据
     * @return 周期数据，下标越大越远，越小越近
     */
    List<Period> selectLastPeriod(int periodNumber);

    /**
     * 根据id获取周期信息
     * @param id 周期唯一标识
     * @return  周期信息
     */
    Period selectPeriod(int id);

    /**
     * 添加周期信息
     * @param period   周期信息（id不需要填）
     * @return 是否成功
     */
    int addPeriod(Period period);

    /**
     * 删除周期信息
     * @param id 周期唯一标识符
     * @return 是否成功
     */
    int deletePeriod(int id);
}
