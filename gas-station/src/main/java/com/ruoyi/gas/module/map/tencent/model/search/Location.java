package com.ruoyi.gas.module.map.tencent.model.search;

/**
 * 经纬度坐标
 *
 * @author KlenKiven
 */
public class Location {

    private double lat;
    private double lng;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "" + lat + "," + lng;
    }
}