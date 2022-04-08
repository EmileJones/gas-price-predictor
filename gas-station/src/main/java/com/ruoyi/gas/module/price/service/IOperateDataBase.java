package com.ruoyi.gas.module.price.service;

import com.ruoyi.gas.module.price.domain.OilSaleData;
import com.ruoyi.gas.module.price.domain.Period;

import java.util.List;
import java.util.Map;

public interface IOperateDataBase {

    /**
     * 添加一组估价数据
     *
     * @param map 石油价格相关的信息 <br>
     *            map中需要有: <br>
     *            "gas_station_id","out_gas_station_id","period_id" <br>
     *            还需要有OilType类的typeName字段    <br><br>
     *
     *            eg: <br><br>
     *            { <br>
     *                "gas_station_id": SXTY0001,    <br>
     *                "out_gas_station_id": SXTYY001,    <br>
     *                "period_id": 1,    <br>
     *                "92号汽油": 20,    <br>
     *                "95号汽油": 90,    <br>
     *                "98号汽油": 70,    <br>
     *                "柴油": 100        <br>
     *            }
     */
    void addPrice(Map<String, String> map);

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
     * 获取历史记录（用户猜的系统外的价格）
     *
     * @param gasStationId 加油站ID
     * @return 某时间内的价格记录 <br><br>
     * eg: <br>
     * [ <br>
     *      {
     *          "gas_station_id": SXTY0001,
     *          "out_gas_station_id": SXTYY001,
     *          "period_id": 2,
     *          "92号汽油": 10,
     *          "95号汽油": 20,
     *          "98号汽油": 30,
     *          "柴油": 40
     *     },   <br>
     *     {
     *          "gas_station_id": SXTY0002,
     *          "out_gas_station_id": SXTYY002,
     *          "period_id": 3,
     *          "92号汽油": 11,
     *          "95号汽油": 22,
     *          "98号汽油": 33,
     *          "柴油": 44
     *     } <br>
     * ] <br>
     */
    List<Map<String, String>> getHistoryPrice(String gasStationId);

    /**
     * 获取某个加油站的某种汽油的历史销售记录
     *
     * @param gasStationId 加油站ID
     * @return 某时间段内的历史销量
     */
    List<OilSaleData> getHistorySaleDataByOilType(String gasStationId);
}
