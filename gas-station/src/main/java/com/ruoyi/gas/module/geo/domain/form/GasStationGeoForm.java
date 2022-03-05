package com.ruoyi.gas.module.geo.domain.form;

/**
 * 查询加油站信息表单
 * @author KlenKiven
 */
public class GasStationGeoForm {

    /** 加油站位置 */
    private String location;

    /** 加油站搜索距离 */
    private Integer distance;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
