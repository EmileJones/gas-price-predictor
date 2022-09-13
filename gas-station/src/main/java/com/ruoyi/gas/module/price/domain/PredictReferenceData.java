package com.ruoyi.gas.module.price.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PredictReferenceData {
    //开始时间
    @JsonFormat(pattern = "yy-MM-dd")
    private Date startTime;
    //截止时间
    @JsonFormat(pattern = "yy-MM-dd")
    private Date endingTime;
    //日均销量
    private Double oilSaleOnPeriod;
    //综合单价
    private Double unitPrice;

    public PredictReferenceData() {
    }

    public PredictReferenceData(Date startTime, Date endingTime, Double oilSaleOnPeriod, Double unitPrice) {
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

    public Double getOilSaleOnPeriod() {
        return oilSaleOnPeriod;
    }

    public void setOilSaleOnPeriod(Double oilSaleOnPeriod) {
        this.oilSaleOnPeriod = oilSaleOnPeriod;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
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
