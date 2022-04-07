package com.ruoyi.gas.module.price.domain;

import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.lang.reflect.Field;

public class GuessPrice {
    private String gasStationId;
    private String outGasStationId;
    private Integer periodId;
    private Double price00;
    private Double price92;
    private Double price95;
    private Double price98;

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }

    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Double getPrice(OilType oilType) {
        Double price = null;
        try {
            Field field = GuessPrice.class.getField("price" + oilType.getTypeNumber());
            price = (Double) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return price;
    }

    public void setPrice(OilType oilType, Double price) {
        try {
            Field field = GuessPrice.class.getField("price" + oilType.getTypeNumber());
            field.set(this, price);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
