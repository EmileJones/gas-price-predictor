package com.ruoyi.gas.module.price.domain;

import java.util.Date;

public class UserPeriod {
    /**
     * 数据唯一标识
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 加油站ID
     */
    private String gasStationId;
    /**
     * 周期时间戳
     */
    private Date timeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
