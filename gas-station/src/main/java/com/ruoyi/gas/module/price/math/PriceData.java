package com.ruoyi.gas.module.price.math;

public class PriceData {
    private Integer gasStationNumber;
    private Integer periodNumber;
    private Double[] routeFactor;
    private Double[] distance;
    private Double[][] outMoney;
    private Double[] inAverageSalesVolume;
    private Double[] inMoney;
    private Double[] presentMoney;
    private Double[] chaseMoney;
    private Double presentAverageSalesVolume;
    private Double inPresentMoney;
    private Double inTargetMoney;

    public PriceData(int gasStationNumber, int periodNumber) {
        this.gasStationNumber = gasStationNumber;
        this.periodNumber = periodNumber;
        routeFactor = new Double[gasStationNumber];
        distance = new Double[gasStationNumber];
        outMoney = new Double[gasStationNumber][periodNumber];
        inAverageSalesVolume = new Double[periodNumber];
        inMoney = new Double[periodNumber];
        presentMoney = new Double[gasStationNumber];
        chaseMoney = new Double[gasStationNumber];
    }

    public void setRouteFactor(int x, Double routeFactor) {this.routeFactor[x] = routeFactor;}
    public void setDistance(int x, Double distance) {this.distance[x] = distance;}
    public void setOutMoney(int x, int n, Double outMoney) {this.outMoney[x][n] = outMoney;}
    public void setInAverageSalesVolume(int n, Double inSalesVolume) {this.inAverageSalesVolume[n] = inSalesVolume;}
    public void setInMoney(int n, Double inMoney) {this.inMoney[n] = inMoney;}
    public void setPresentMoney(int x, Double presentMoney) {this.presentMoney[x] = presentMoney;}
    public void setChaseMoney(int x, Double chaseMoney) {this.chaseMoney[x] = chaseMoney;}
    public void setPresentAverageSalesVolume(Double presentAverageSalesVolume) {this.presentAverageSalesVolume = presentAverageSalesVolume;}
    public void setInPresentMoney(Double inPresentMoney) {this.inPresentMoney = inPresentMoney;}
    public void setInTargetMoney(Double inTargetMoney) {this.inTargetMoney = inTargetMoney;}

    public Integer getGasStationNumber() {return gasStationNumber;}
    public Integer getPeriodNumber() {return periodNumber;}
    public Double getRouteFactor(int x) {return routeFactor[x];}
    public Double getDistance(int x) {return distance[x];}
    public Double getOutMoney(int x, int n) {return outMoney[x][n];}
    public Double getInSalesVolume(int n) {return inAverageSalesVolume[n];}
    public Double getInMoney(int n) {return inMoney[n];}
    public Double getPresentMoney(int x) {return presentMoney[x];}
    public Double getChaseMoney(int x) {return chaseMoney[x];}
    public Double getPresentAverageSalesVolume() {return presentAverageSalesVolume;}
    public Double getInPresentMoney() {return inPresentMoney;}
    public Double getInTargetMoney() {return inTargetMoney;}
}
