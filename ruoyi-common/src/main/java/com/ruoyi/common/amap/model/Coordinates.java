package com.ruoyi.common.amap.model;

/**
 * 经纬度
 * @author klenkiven
 */
public class Coordinates {

    /**
     * 经度
     */
    private final Double longitude;

    /**
     * 纬度
     */
    private final Double latitude;

    public Coordinates(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return String.format("%.6f,%.6f", longitude, latitude);
    }
}
