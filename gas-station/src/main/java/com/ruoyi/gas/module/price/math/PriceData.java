package com.ruoyi.gas.module.price.math;

public class PriceData {
    private Integer gasStationNumber;
    private Integer periodNumber;
    private Double[] routeFactor;
    private Double[] distance;
    private Double[][] outAverageMoney;
    private Double[] inSalesVolume;
    private Double[] inMoney;
    private Double[] outPresentMoney;
    private Double[] outChaseMoney;
    private Double inPresentAverageSalesVolume;
    private Double inPresentMoney;
    private Double inTargetMoney;

    public PriceData(int gasStationNumber, int periodNumber) {
        this.gasStationNumber = gasStationNumber;
        this.periodNumber = periodNumber;
        routeFactor = new Double[gasStationNumber];
        distance = new Double[gasStationNumber];
        outAverageMoney = new Double[gasStationNumber][periodNumber];
        inSalesVolume = new Double[periodNumber];
        inMoney = new Double[periodNumber];
        outPresentMoney = new Double[gasStationNumber];
        outChaseMoney = new Double[gasStationNumber];
    }

    public void setRouteFactor(int x, Double routeFactor) {this.routeFactor[x] = routeFactor;}
    public void setDistance(int x, Double distance) {this.distance[x] = distance;}
    public void setOutAverageMoney(int x, int n, Double outMoney) {this.outAverageMoney[x][n] = outMoney;}
    public void setInAverageSalesVolume(int n, Double inSalesVolume) {this.inSalesVolume[n] = inSalesVolume;}
    public void setInMoney(int n, Double inMoney) {this.inMoney[n] = inMoney;}
    public void setPresentMoney(int x, Double presentMoney) {this.outPresentMoney[x] = presentMoney;}
    public void setChaseMoney(int x, Double chaseMoney) {this.outChaseMoney[x] = chaseMoney;}
    public void setInPresentAverageSalesVolume(Double inPresentAverageSalesVolume) {this.inPresentAverageSalesVolume = inPresentAverageSalesVolume;}
    public void setInPresentMoney(Double inPresentMoney) {this.inPresentMoney = inPresentMoney;}
    public void setInTargetMoney(Double inTargetMoney) {this.inTargetMoney = inTargetMoney;}

    public Integer getGasStationNumber() {return gasStationNumber;}
    public Integer getPeriodNumber() {return periodNumber;}
    public Double getRouteFactor(int x) {return routeFactor[x];}
    public Double getDistance(int x) {return distance[x];}
    public Double getOutAverageMoney(int x, int n) {return outAverageMoney[x][n];}
    public Double getInSalesVolume(int n) {return inSalesVolume[n];}
    public Double getInMoney(int n) {return inMoney[n];}
    public Double getOutPresentMoney(int x) {return outPresentMoney[x];}
    public Double getOutChaseMoney(int x) {return outChaseMoney[x];}
    public Double getInPresentAverageSalesVolume() {return inPresentAverageSalesVolume;}
    public Double getInPresentMoney() {return inPresentMoney;}
    public Double getInTargetMoney() {return inTargetMoney;}
}
