package com.ruoyi.gas.module.price.domain;

import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.joda.time.DateTime;

import java.lang.reflect.Field;

public class Period {
    private int id;
    private DateTime startTime;
    private DateTime endTime;
    private Double price92;
    private Double price95;
    private Double price98;
    private Double price00;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        if(endTime == null){
            return new DateTime();
        }
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public Double getPrice(OilType oilType) {
        Double price = null;
        try {
            Field field = Period.class.getField("price" + oilType.getTypeNumber());
            price = (Double) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return price;
    }

    public void setPrice(OilType oilType, Double price) {
        try {
            Field field = Period.class.getField("price" + oilType.getTypeNumber());
            field.set(this, price);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public int getDValueOfDay() {
        long dValue = endTime.getMillis() - startTime.getMillis();
        return (int) (dValue / 1000 / 60 / 60 / 24);
    }
}
