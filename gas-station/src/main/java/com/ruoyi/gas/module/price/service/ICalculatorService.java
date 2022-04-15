package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import org.joda.time.DateTime;


public interface ICalculatorService {
    /**
     * 计算日均销量
     *
     * @param data 用户输入的信息封装
     * @return 日均销量
     */
    double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException;


    /**
     * 获取某期间内的某种石油的总销量(系统内)
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
     * 获取某期间内的某种石油的总收入(系统内)
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
