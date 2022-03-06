package com.ruoyi.gas.module.map.model;

/**
 * 路线代价
 * @author KlenKiven
 */
public class PathCost {

    /** 两个地点之间的路线距离(单位米) */
    private Integer distance;

    /** 两个地点之间需要经过的红绿灯数 */
    private Integer trafficLight;

    /** 两个地点之间的路径方向变化 */
    private String[] routeDirection;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(Integer trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String[] getRouteDirection() {
        return routeDirection;
    }

    public void setRouteDirection(String[] routeDirection) {
        this.routeDirection = routeDirection;
    }
}
