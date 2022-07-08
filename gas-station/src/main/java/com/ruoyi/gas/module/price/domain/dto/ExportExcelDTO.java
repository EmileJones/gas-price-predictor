package com.ruoyi.gas.module.price.domain.dto;

import com.ruoyi.gas.module.price.domain.framwork.OilType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExportExcelDTO {
    private String outGasStationId;
    private String outGasStationName;
    private Double price92;
    private Double price95;
    private Double price98;
    private Double price00;

    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
    }

    public String getOutGasStationName() {
        return outGasStationName;
    }

    public void setOutGasStationName(String outGasStationName) {
        this.outGasStationName = outGasStationName;
    }

    public Double getPrice92() {
        return price92;
    }

    public void setPrice92(Double price92) {
        this.price92 = price92;
    }

    public Double getPrice95() {
        return price95;
    }

    public void setPrice95(Double price95) {
        this.price95 = price95;
    }

    public Double getPrice98() {
        return price98;
    }

    public void setPrice98(Double price98) {
        this.price98 = price98;
    }

    public Double getPrice00() {
        return price00;
    }

    public void setPrice00(Double price00) {
        this.price00 = price00;
    }

    public Double getPrice(OilType oilType) {
        Double result = null;
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod("getPrice" + oilType.getTypeNumber());
            Object invoke = declaredMethod.invoke(this);
            result = (Double) invoke;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            result = new Double(0);
        }
        return result;
    }
}
