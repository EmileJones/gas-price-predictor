package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.PredictReferenceData;
import com.ruoyi.gas.module.price.domain.dto.DataForCalculation;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import com.ruoyi.gas.module.price.domain.vo.OpponentPriceDataVO;
import com.ruoyi.gas.module.price.domain.vo.PriceDataVO;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;
import com.ruoyi.gas.module.price.math.PriceMath;
import org.joda.time.DateTime;

import java.util.List;

/**
 * 用于计算 最终价格 的类
 */
public interface ICalculatorService {
    long REFERENCE_DATA_NUMBER = 3;

    /**
     * 计算日均销量（传入经过处理后的数据）
     *
     * @param data 用户输入的信息封装
     * @return 相关数据
     */
    PriceMath getAverageSalesVolume(DataForCalculation data);

    /**
     * 计算日均销量（传入原始数据）
     *
     * @param userId      用户ID
     * @param priceDataVO 用户输入的数据
     * @return 相关数据
     */
    PriceMath getAverageSalesVolume(Long userId, PriceDataVO priceDataVO);

    /**
     * 获取近三个周期的综合单价以及日均销量
     * @param userId 用户ID
     * @param gasStationId 系统内加油站ID
     * @param oilType 石油类型
     * @return 近三个周期的参考数据
     */
    List<PredictReferenceData> getReferenceData(Long userId, String gasStationId, OilType oilType);
}
