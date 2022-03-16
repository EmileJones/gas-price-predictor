package com.ruoyi.gas.module.price.exception;

public class DataIsExistException extends Exception {
    private String gasStationId;
    private String outGasStationId;
    private int periodId;

    public DataIsExistException() {
    }

    public DataIsExistException(String message) {
        super(message);
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }

    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }
}
