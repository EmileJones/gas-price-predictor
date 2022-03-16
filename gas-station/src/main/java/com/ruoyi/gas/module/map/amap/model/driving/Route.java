package com.ruoyi.gas.module.map.amap.model.driving;

import java.util.List;

/**
 * 规划方案列表
 * @author klenkiven
 */
public class Route {

    /**
     * 起点经纬度
     */
    private String origin;

    /**
     * 终点经纬度
     */
    private String destination;

    /**
     * 算路方案详情
     */
    private List<Paths> paths;

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getOrigin() {
        return origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDestination() {
        return destination;
    }

    public void setPaths(List<Paths> paths) {
        this.paths = paths;
    }
    public List<Paths> getPaths() {
        return paths;
    }

}
