package com.ruoyi.gas.module.price.domain.vo;

import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.util.List;

public class PriceDataVO {
    /**
     * 系统内加油站ID
     */
    private String stationId;
    /**
     * 目标价格
     */
    private Double targetMoney;
    /**
     * 当前价格
     */
    private Double presentMoney;
    /**
     * 当前平均销量
     */
    private Double presentAverageSalesVolume;
    /**
     * 预测对手的价格
     */
    private List<OpponentPriceDataVO> opponentPriceData;
    /**
     * 石油类型
     */
    private OilType oilType;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getPresentMoney() {
        return presentMoney;
    }

    public void setPresentMoney(Double presentMoney) {
        this.presentMoney = presentMoney;
    }

    public Double getPresentAverageSalesVolume() {
        return presentAverageSalesVolume;
    }

    public void setPresentAverageSalesVolume(Double presentAverageSalesVolume) {
        this.presentAverageSalesVolume = presentAverageSalesVolume;
    }

    public List<OpponentPriceDataVO> getOpponentPriceData() {
        return opponentPriceData;
    }

    public void setOpponentPriceData(List<OpponentPriceDataVO> opponentPriceData) {
        this.opponentPriceData = opponentPriceData;
    }

    public OilType getOilType() {
        return oilType;
    }

    public void setOilType(OilType oilType) {
        this.oilType = oilType;
    }

    @Override
    public String toString() {
        return "PriceDataVO{" +
                "stationId='" + stationId + '\'' +
                ", targetMoney=" + targetMoney +
                ", presentMoney=" + presentMoney +
                ", presentAverageSalesVolume=" + presentAverageSalesVolume +
                ", opponentPriceData=" + opponentPriceData +
                ", oilType=" + oilType +
                '}';
    }
}
