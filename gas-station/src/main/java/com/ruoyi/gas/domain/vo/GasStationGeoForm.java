package com.ruoyi.gas.domain.vo;

/**
 * 查询加油站信息表单
 * @author KlenKiven
 */
public class GasStationGeoForm {

    /** 加油站位置 */
    private String location;

    /** 加油站搜索半径 */
    private Integer radius;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}
