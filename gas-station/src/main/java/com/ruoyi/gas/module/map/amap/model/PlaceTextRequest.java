package com.ruoyi.gas.module.map.amap.model;

/**
 * 关键字搜索
 * @author KlenKiven
 */
public class PlaceTextRequest {

    /** 高德地图开发者需要的KEY */
    private String key = "";

    /** 城市的ADCODE
     * <p>参考：<a href="https://github.com/Vonng/adcode">中国行政区划代码</a></p>
     */
    private String city = "";

    /** 搜索关键词（必填：默认为空） */
    private String keywords = "";

    /** 搜索地区的类型代码：默认为加油站 */
    private String types = "010100";

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
