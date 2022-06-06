package com.ruoyi.gas.module.price.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.joda.time.DateTime;

public class Period extends BaseEntity {
    private Integer id;
    private DateTime startTime;

    /** 价格是否上调 */
    private Boolean rise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public Boolean getRise() {
        return rise;
    }

    public void setRise(Boolean rise) {
        this.rise = rise;
    }

}
