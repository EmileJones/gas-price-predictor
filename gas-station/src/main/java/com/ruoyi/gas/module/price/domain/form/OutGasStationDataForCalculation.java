package com.ruoyi.gas.module.price.domain.form;

public class OutGasStationDataForCalculation {
    /**
     * 系统外加油站ID
     */
    private String outGasStationId;
    /**
     * 目前价格
     */
    private Double presentMoney;
    /**
     * 跟随价格
     */
    private Double chaseMoney;
    /**
     * 距离影响因素
     */
    private Double routeFactor;
    /**
     * 距离
     */
    private Double distance;


    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
    }

    public Double getPresentMoney() {
        return presentMoney;
    }

    public void setPresentMoney(Double presentMoney) {
        this.presentMoney = presentMoney;
    }

    public Double getChaseMoney() {
        return chaseMoney;
    }

    public void setChaseMoney(Double chaseMoney) {
        this.chaseMoney = chaseMoney;
    }

    public Double getRouteFactor() {
        return routeFactor;
    }

    public void setRouteFactor(Double routeFactor) {
        this.routeFactor = routeFactor;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
