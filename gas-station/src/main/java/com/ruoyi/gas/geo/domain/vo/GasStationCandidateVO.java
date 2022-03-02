package com.ruoyi.gas.geo.domain.vo;

/**
 * 候选加油站信息
 * @author KlenKiven
 */
public class GasStationCandidateVO {

    /** 加油站经纬度信息 */
    private String location;

    /** 加油站名称 */
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
}
