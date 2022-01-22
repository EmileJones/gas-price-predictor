package com.ruoyi.common.amap.model;


/**
 * Place Search -- Around
 * @author klenkiven
 */
public class PlaceAroundRequest {

    private String key = "";

    private String location = "";

    private String types = "";

    private Integer radius = 3500;

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
