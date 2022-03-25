package com.ruoyi.gas.module.map.baidu.model;


/**
 * 百度路线导航请求参数
 * @author klenkiven
 */
public class DrivingRequest {

    private String ak = "";

    /**
     * 起点经纬度
     */
    private String origin = "";

    /**
     * 目的地经纬度
     */
    private String destination = "";

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
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

    @Override
    public String toString() {
        return "?ak=" + ak +
                "&origin=" + origin +
                "&destination=" + destination +
                "&output=json";
    }
}
