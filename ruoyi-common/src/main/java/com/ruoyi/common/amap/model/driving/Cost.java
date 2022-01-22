package com.ruoyi.common.amap.model.driving;

/**
 * 设置后可返回方案所需时间及费用成本
 * @author klenkiven
 */
public class Cost {

    /**
     * 线路耗时，包括方案总耗时及分段step中的耗时
     */
    private Integer duration;

    /**
     * 方案中红绿灯个数，单位：个
     */
    private Integer traffic_lights;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTraffic_lights() {
        return traffic_lights;
    }

    public void setTraffic_lights(Integer traffic_lights) {
        this.traffic_lights = traffic_lights;
    }
}
