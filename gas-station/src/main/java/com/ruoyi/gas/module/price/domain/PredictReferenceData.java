package com.ruoyi.gas.module.price.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PredictReferenceData {
    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    //截止时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endingTime;
    //日均销量
    private double oilSaleOnPeriod;
    //综合单价
    private double unitPrice;

    public PredictReferenceData() {
    }

    public PredictReferenceData(Date startTime, Date endingTime, double oilSaleOnPeriod, double unitPrice) {
        this.startTime = startTime;
        this.endingTime = endingTime;
        this.oilSaleOnPeriod = oilSaleOnPeriod;
        this.unitPrice = unitPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public double getOilSaleOnPeriod() {
        return oilSaleOnPeriod;
    }

    public void setOilSaleOnPeriod(double oilSaleOnPeriod) {
        this.oilSaleOnPeriod = oilSaleOnPeriod;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "PredictReferenceData{" +
                "startTime=" + startTime +
                ", endingTime=" + endingTime +
                ", oilSaleOnPeriod=" + oilSaleOnPeriod +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
