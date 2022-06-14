package com.ruoyi.gas.module.price.domain.vo;

public class OpponentPriceDataVO {
    /**
     * 系统外加油站ID
     */
    private String outSystemGasStationId;
    /**
     * 系统外加油站名称
     */
    private String outSystemGasStationName;
    /**
     * 跟随价格
     */
    private Double chaseMoney;
    /**
     * 当前价格
     */
    private Double presentMoney;

    public String getOutSystemGasStationId() {
        return outSystemGasStationId;
    }

    public void setOutSystemGasStationId(String outSystemGasStationId) {
        this.outSystemGasStationId = outSystemGasStationId;
    }

    public String getOutSystemGasStationName() {
        return outSystemGasStationName;
    }

    public void setOutSystemGasStationName(String outSystemGasStationName) {
        this.outSystemGasStationName = outSystemGasStationName;
    }

    public Double getChaseMoney() {
        return chaseMoney;
    }

    public void setChaseMoney(Double chaseMoney) {
        this.chaseMoney = chaseMoney;
    }


    public Double getPresentMoney() {
        return presentMoney;
    }

    public void setPresentMoney(Double presentMoney) {
        this.presentMoney = presentMoney;
    }

    @Override
    public String toString() {
        return "PriceDataVO{" +
                "outSystemGasStationId='" + outSystemGasStationId + '\'' +
                ", outSystemGasStationName='" + outSystemGasStationName + '\'' +
                ", chaseMoney=" + chaseMoney +
                ", presentMoney=" + presentMoney +
                '}';
    }
}
