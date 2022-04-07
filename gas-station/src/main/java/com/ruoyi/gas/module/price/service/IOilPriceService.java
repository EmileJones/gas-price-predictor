package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.Period;
import com.ruoyi.gas.module.price.domain.form.DataForCalculation;
import com.ruoyi.gas.module.price.domain.GuessPrice;
import com.ruoyi.gas.module.price.exception.DataIsExistException;
import com.ruoyi.gas.module.price.exception.DataIsNotEnoughException;

import java.util.List;

public interface IOilPriceService {
    /**
     * 计算日均销量
     *
     * @param data 用户输入的信息封装
     * @return 日均销量
     */
    double getAverageSalesVolume(DataForCalculation data) throws DataIsNotEnoughException;

    /**
     * 添加一组估价数据
     *
     * @param guessPrice 石油价格
     */
    @Deprecated
    void addPrice(GuessPrice guessPrice) throws DataIsExistException;

    /**
     * 添加系统内加油站的销售记录 <br>
     * id是数据库自增的，不用填
     *
     * @param oilSaleData 销售记录
     */
    void addOilSaleData(OilSaleData oilSaleData);

    /**
     * 添加周期信息 <br>
     * startTime为必须字段 <br>
     * endTime为null则代此周期是最新周期，结束时间是当前时间，直到添加下一个周期为止 <br>
     * id是数据库自增的，不用填
     *
     * @param period 周期信息
     */
    void addPeriod(Period period);

    /**
     * 获取某个时间段内（用户猜的系统外）的价格记录
     *
     * @param gasStationId 加油站ID
     * @return 某时间内的价格记录
     */
    @Deprecated
    List<GuessPrice> getHistoryPrice(String gasStationId);

    /**
     * 获取某个加油站的某种汽油的历史销售记录
     *
     * @param gasStationId 加油站ID
     * @return 某时间段内的历史销量
     */
    List<OilSaleData> getHistorySaleDataByOilType(String gasStationId);

    /**
     * 获取历史周期信息（降序排列）
     *
     * @param periodNumber 需要获得的历史数据数量
     * @return 周期信息
     */
    List<Period> getHistoryPeriod(int periodNumber);
}
