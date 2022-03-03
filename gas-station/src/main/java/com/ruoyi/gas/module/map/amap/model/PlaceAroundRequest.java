package com.ruoyi.gas.module.map.amap.model;


/**
 * 地区搜索 -- 周边搜索
 * @author klenkiven
 */
public class PlaceAroundRequest {

    /** 高德地图开发者需要的KEY */
    private String key = "";

    private String location = "";

    /** 搜索地区的类型代码：默认为加油站 */
    private String types = "010100";

    /** 搜索半径：默认更大 */
    private Integer radius = 4000;

    /** 搜索关键词 */
    private String keywords = "";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "?key=" + key +
                "&location=" + location +
                "&types=" + types +
                "&radius=" + radius +
                "&keywords=" + keywords;
    }
}
