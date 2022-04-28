package com.ruoyi.gas.module.price.service;

import java.util.List;
import java.util.Map;

@Deprecated
public interface IOilPriceService {

    /**
     * 添加一组估价数据
     *
     * @param map 石油价格相关的信息 <br>
     *            map中需要有: <br>
     *            "gas_station_id","out_gas_station_id","period_id" <br>
     *            还需要有OilType类的typeName字段,无则代表默认为0    <br><br>
     *
     *            eg: <br>
     *            { <br>
     *                "gas_station_id": SXTY0001,    <br>
     *                "out_gas_station_id": SXTYY001,    <br>
     *                "period_id": 1,    <br>
     *                "92号汽油": 20,    <br>
     *                "95号汽油": 90,    <br>
     *                "98号汽油": 70,    <br>
     *                "柴油": 100        <br>
     *            }
     *
     * @return 返回添加成功的数据量
     */
    int addPrice(Map<String, String> map);




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
     * 修改一组数据
     * @param map 一组数据 <br><br>
     *        map中需要有: <br>
     *        "gas_station_id","out_gas_station_id","period_id" <br>
     *        OilType类的typeName字段可有可无,有则代表需要修改，无则代表不需要修改    <br><br>
     *
     *        eg: <br>
     *            修改92号汽油的价格<br>
     *        { <br>
     *            "gas_station_id": SXTY0001,    <br>
     *            "out_gas_station_id": SXTYY001,    <br>
     *            "period_id": 1,    <br>
     *            "92号汽油": 20,    <br>
     *        }
     * @return 修改成功的数据数
     */
    int updatePrice(Map<String, String> map);
}
