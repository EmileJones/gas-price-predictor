package com.ruoyi.gas.module.geo.domain.form;

/**
 * 添加用户加油站前端提交的表单
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public class GasStationAddForm {

    private String location;

    private String stationName;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "GasStationAddForm{" +
                "location='" + location + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
