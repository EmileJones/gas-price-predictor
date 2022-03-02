package com.ruoyi.common.amap.model;

import com.ruoyi.common.amap.model.place.POI;

import java.util.List;

/**
 * 搜索POI -- Result
 * @author klenkiven
 */
public class PlaceAroundResult {

    /**
     * Info Code
     */
    private String infocode;

    /**
     * 搜索方案数目(最大值为1000)
     */
    private Integer count;

    /**
     * 搜索POI信息列表
     */
    private List<POI> pois;

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<POI> getPois() {
        return pois;
    }

    public void setPois(List<POI> pois) {
        this.pois = pois;
    }
}
