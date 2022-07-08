package com.ruoyi.gas.module.price.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.util.Date;

public class CalculationLog extends BaseEntity {
    /**
     * 数据唯一标识
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 加油站id
     */
    private String gasStationId;
    /**
     * 时间戳
     */
    private Date timeStamp;
    /**
     * 计算参数
     */
    private Double param;
    /**
     * 石油类型
     */
    private OilType oilType;

    public OilType getOilType() {
        return oilType;
    }

    public void setOilType(OilType oilType) {
        this.oilType = oilType;
    }

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

    public Double getParam() {
        return param;
    }

    public void setParam(Double param) {
        this.param = param;
    }
}
