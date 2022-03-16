package com.ruoyi.gas.module.price.domain;

import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.joda.time.DateTime;

public class OilSaleData {
    /**
     * 唯一标识符
     */
    private int id;
    /**
     * 加油站id
     */
    private String gasStationId;
    /**
     * 石油种类
     */
    private OilType oilType;
    /**
     * 销售数量L
     */
    private double number;
    /**
     * 销售金额
     */
    private double price;
    /**
     * 时间
     */
    private DateTime date;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public OilType getOilType() {return oilType;}
    public void setOilType(OilType oilType) {this.oilType = oilType;}

    public String getGasStationId() {return gasStationId;}
    public void setGasStationId(String gasStationId) {this.gasStationId = gasStationId;}

    public DateTime getDate() {return date;}
    public void setDate(DateTime date) {this.date = date;}

    public double getNumber() {return number;}
    public void setNumber(double number) {this.number = number;}
}
