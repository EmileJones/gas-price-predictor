package com.ruoyi.gas.module.price.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class OpponentPrice extends BaseEntity {

    /**
     * 数据唯一标识
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 系统内加油站id
     */
    private String gasStationId;

    /**
     * 系统外加油站id
     */
    private String outGasStationId;

    /**
     * 时间戳
     */
    private Date timeStamp;

    /**
     * 石油价钱
     */
    private BigDecimal price92;
    private BigDecimal price95;
    private BigDecimal price98;
    private BigDecimal price00;

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

    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getPrice92() {
        return price92;
    }

    public void setPrice92(BigDecimal price92) {
        this.price92 = price92;
    }

    public BigDecimal getPrice95() {
        return price95;
    }

    public void setPrice95(BigDecimal price95) {
        this.price95 = price95;
    }

    public BigDecimal getPrice98() {
        return price98;
    }

    public void setPrice98(BigDecimal price98) {
        this.price98 = price98;
    }

    public BigDecimal getPrice00() {
        return price00;
    }

    public void setPrice00(BigDecimal price00) {
        this.price00 = price00;
    }
}
