package com.ruoyi.gas.module.map.amap.model;


/**
 * Direction Request 2.0
 * @author klenkiven
 */
public class DrivingDirectionRequest {

    /**
     * 高德Key
     */
    private String key = "";

    /**
     * 起点经纬度
     */
    private String origin = "";

    /**
     * 目的地经纬度
     */
    private String destination = "";

    /**
     * 返回结果控制
     * 默认："cost"
     */
    private String show_fields = "cost";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getShow_fields() {
        return show_fields;
    }

    public void setShow_fields(String show_fields) {
        this.show_fields = show_fields;
    }

    @Override
    public String toString() {
        return "?key=" + key +
                "&origin=" + origin +
                "&destination=" + destination +
                "&show_fields=" + show_fields;
    }
}
