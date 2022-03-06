package com.ruoyi.gas.module.map.amap.model.driving;

/**
 * 路线分段
 * @author klenkiven
 */
public class Step {

    /**
     * 进入道路方向
     */
    private String orientation;

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    /**
     * 道路名称
     */
    private String road_name;

    /**
     *分段距离信息
     */
    private Integer step_distance;

    public Integer getStep_distance() {
        return step_distance;
    }

    public void setStep_distance(Integer step_distance) {
        this.step_distance = step_distance;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
