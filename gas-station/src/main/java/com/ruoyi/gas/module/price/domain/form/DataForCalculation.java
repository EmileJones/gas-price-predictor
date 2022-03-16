package com.ruoyi.gas.module.price.domain.form;

import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.util.List;

public class DataForCalculation {
    /**
     * 用户加油站ID
     */
    private String gasStationId;
    /**
     * 系统外加油站相关参数
     */
    private List<OutGasStationDataForCalculation> outSystemData;
    /**
     * 计算时的周期数
     */
    private Integer periodNumber;
    /**
     * 当前价格
     */
    private Double presentMoney;
    /**
     * 目标价格
     */
    private Double targetMoney;
    /**
     * 日均销量
     */
    private Double presentAverageSalesVolume;

    /**
     * 石油类型
     */
    private OilType oilType;

    public List<OutGasStationDataForCalculation> getOutSystemData() {
        return outSystemData;
    }

    public void setOutSystemData(List<OutGasStationDataForCalculation> outSystemData) {
        this.outSystemData = outSystemData;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public Double getPresentMoney() {
        return presentMoney;
    }

    public void setPresentMoney(Double presentMoney) {
        this.presentMoney = presentMoney;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getPresentAverageSalesVolume() {
        return presentAverageSalesVolume;
    }

    public void setPresentAverageSalesVolume(Double presentAverageSalesVolume) {
        this.presentAverageSalesVolume = presentAverageSalesVolume;
    }

    public OilType getOilType() {
        return oilType;
    }

    public void setOilType(OilType oilType) {
        this.oilType = oilType;
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }
}
