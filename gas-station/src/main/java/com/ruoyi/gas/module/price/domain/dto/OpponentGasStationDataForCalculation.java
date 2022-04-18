package com.ruoyi.gas.module.price.domain.dto;

public class OpponentGasStationDataForCalculation {
    /**
     * 系统外加油站ID
     */
    private String opponentGasStationId;
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


    public String getOpponentGasStationId() {
        return opponentGasStationId;
    }

    public void setOpponentGasStationId(String opponentGasStationId) {
        this.opponentGasStationId = opponentGasStationId;
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
