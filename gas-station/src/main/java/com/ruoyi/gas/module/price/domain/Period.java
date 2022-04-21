package com.ruoyi.gas.module.price.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.joda.time.DateTime;

import java.lang.reflect.Field;

public class Period extends BaseEntity {
    private int id;
    private DateTime startTime;
    private DateTime endTime;

    /** 价格是否上调 */
    private Boolean isRise;

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
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getRise() {
        return isRise;
    }

    public void setRise(Boolean rise) {
        isRise = rise;
    }

    public int getDValueOfDay() {
        long dValue = endTime.getMillis() - startTime.getMillis();
        return (int) (dValue / 1000 / 60 / 60 / 24);
    }
}
