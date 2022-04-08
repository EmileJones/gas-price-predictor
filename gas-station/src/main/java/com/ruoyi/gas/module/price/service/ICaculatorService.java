package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

public interface ICaculatorService {
    /**
     * 计算日均销量
     *
     * @param data 用户输入的信息封装
     * @return 日均销量
     */
    double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException;



    /**
     * 获取历史周期信息（降序排列）
     *
     * @param periodNumber 需要获得的历史数据数量
     * @return 周期信息
     */
    List<Period> getHistoryPeriod(int periodNumber);

    /**
     * 获取某期间内的某种石油的总销量
     *
     * @param gasStationId 加油站ID
     * @param oilType      石油类型
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return 总销量
     */
    double getTotalPrice(String gasStationId,
                         OilType oilType,
                         DateTime startTime,
                         DateTime endTime);

    /**
     * 获取某期间内的某种石油的总收入
     *
     * @param gasStationId 加油站ID
     * @param oilType      石油类型
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return 总收入
     */
    double getTotalSalesVolume(String gasStationId,
                               OilType oilType,
                               DateTime startTime,
                               DateTime endTime);
}
