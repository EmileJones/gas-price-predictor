package com.ruoyi.gas.module.price.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.util.Date;

public class OilSaleDataVO extends BaseEntity {
    /**
     * 唯一标识符
     */
    private Integer id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 加油站id
     */
    private String gasStationId;
    /**
     * 加油站名称
     */
    private String gasStationName;
    /**
     * 石油种类
     */
    private String oilType;
    /**
     * 销售数量L
     */
    private Double lNumber;
    /**
     * 销售数量Kg
     */
    private Double kgNumber;
    /**
     * 销售金额
     */
    private Double price;
    /**
     * 时间
     */
    private String date;

    /**
     * 批号(用于回滚)
     */
    private Integer batch;

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}

    public String getOilType() {return oilType;}
    public void setOilType(String oilType) {this.oilType = oilType;}

    public String getGasStationId() {return gasStationId;}
    public void setGasStationId(String gasStationId) {this.gasStationId = gasStationId;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getGasStationName() {return gasStationName;}
    public void setGasStationName(String gasStationName) {this.gasStationName = gasStationName;}

    public Integer getBatch() {return batch;}
    public void setBatch(Integer batch) {this.batch = batch;}


    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}

    public void setlNumber(Double lNumber) {this.lNumber = lNumber;}
    public Double getlNumber() {return lNumber;}

    public Double getKgNumber() {return kgNumber;}
    public void setKgNumber(Double kgNumber) {this.kgNumber = kgNumber;}
}
