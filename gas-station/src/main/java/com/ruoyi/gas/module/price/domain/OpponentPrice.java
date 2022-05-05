package com.ruoyi.gas.module.price.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class OpponentPrice extends BaseEntity {

    /**
     * 数据唯一标识
     */
    private Long id;

    /**
     * 用户周期ID
     */
    private Long userPeriodId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 系统内加油站ID
     */
    private String gasStationId;
    /**
     * 系统外加油站id
     */
    private String outGasStationId;

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

    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
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

    public Long getUserPeriodId() {
        return userPeriodId;
    }

    public void setUserPeriodId(Long userPeriodId) {
        this.userPeriodId = userPeriodId;
    }


    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice(OilType oilType) {
        BigDecimal result = null;
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod("getPrice" + oilType.getTypeNumber());
            Object invoke = declaredMethod.invoke(this);
            result = (BigDecimal) invoke;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            result = new BigDecimal(0);
        }
        return result;
    }
}
