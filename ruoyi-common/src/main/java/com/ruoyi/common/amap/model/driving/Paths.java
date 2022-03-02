package com.ruoyi.common.amap.model.driving;

import java.util.List;

/**
 * 算路方案详情
 * @author klenkiven
 */
public class Paths {

    /**
     * 方案距离，单位：米
     */
    private Integer distance;

    /**
     * 0 代表限行已规避或未限行，即该路线没有限行路段
     * 1 代表限行无法规避，即该线路有限行路段
     */
    private String restriction;

    /**
     * 所需时间及费用成本
     */
    private Cost cost;

    /**
     *
     */
    private List<Step> steps;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
