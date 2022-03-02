package com.ruoyi.gas.module.map.amap.model;

/**
 * 关键字搜索
 * @author KlenKiven
 */
public class PlaceTextRequest {

    private String key = "";

    private String city = "";

    private String keywords = "";

    private String types = "";

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                "&city=" + city +
                "&keywords=" + keywords +
                "&types=" + types;
    }
}
