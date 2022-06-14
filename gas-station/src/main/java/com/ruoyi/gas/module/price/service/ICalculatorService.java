package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.domain.vo.OpponentPriceDataVO;
import com.ruoyi.gas.module.price.domain.vo.PriceDataVO;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import org.joda.time.DateTime;

import java.util.List;

/**
 * 用于计算 最终价格 的类
 */
public interface ICalculatorService {
    /**
     * 计算日均销量（传入经过处理后的数据）
     *
     * @param data 用户输入的信息封装
     * @return 日均销量
     */
    double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException;

//
//    /**
//     * 获取某期间内的某种石油的总销量(系统内)
//     *
//     * @param gasStationId 加油站ID
//     * @param oilType      石油类型
//     * @param startTime    开始时间
//     * @param endTime      结束时间
//     * @return 总销量
//     */
//    double getTotalPrice(String gasStationId,
//                         OilType oilType,
//                         DateTime startTime,
//                         DateTime endTime);
//
//    /**
//     * 获取某期间内的某种石油的总收入(系统内)
//     *
//     * @param gasStationId 加油站ID
//     * @param oilType      石油类型
//     * @param startTime    开始时间
//     * @param endTime      结束时间
//     * @return 总收入
//     */
//    double getTotalSalesVolume(String gasStationId,
//                               OilType oilType,
//                               DateTime startTime,
//                               DateTime endTime);

    /**
     * 计算日均销量（传入原始数据）
     *
     * @param userId      用户ID
     * @param priceDataVO 用户输入的数据
     * @return 日均销量
     */
    double getAverageSalesVolume(Long userId, PriceDataVO priceDataVO);
}
