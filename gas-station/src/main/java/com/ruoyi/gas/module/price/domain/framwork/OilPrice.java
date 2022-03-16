package com.ruoyi.gas.module.price.domain.framwork;

public class OilPrice {
    private String gasStationId;
    private String outGasStationId;
    private int periodId;

    private OilType oilType;
    private Double price;

    public String getOutGasStationId() {return outGasStationId;}
    public void setOutGasStationId(String outGasStationId) {this.outGasStationId = outGasStationId;}

    public String getGasStationId() {return gasStationId;}
    public void setGasStationId(String gasStationId) {this.gasStationId = gasStationId;}

    public OilType getOilType() {return oilType;}
    public void setOilType(OilType oilType) {this.oilType = oilType;}

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}

    public int getPeriodId() {return periodId;}
    public void setPeriodId(int periodId) {this.periodId = periodId;}
}
