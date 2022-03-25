package com.ruoyi.gas.module.map.baidu.model;

/**
 * 百度行政区域搜索请求
 * @author KlenKiven
 */
public class AroundSearchRequest {

    private String ak;

    /** 搜索位置 */
    private String location;

    /** 搜索半径 */
    private Integer radius;

    /** 搜索地点的类型 */
    private final String tag = "加油站";

    /** 只搜索某个行政区域内的目标 */
    private final Boolean city_limit = false;

    /** 页面大小为20条 */
    private final Integer page_size = 20;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public String getTag() {
        return tag;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public Boolean getCity_limit() {
        return city_limit;
    }

    public Integer getPage_size() {
        return page_size;
    }

    @Override
    public String toString() {
        return "?ak=" + ak + '&' +
                "location=" + location + "&" +
                "radius=" + radius + "&" +
                "query=," + tag + '&' +
                "city_limit=" + city_limit + '&' +
                "page_size=" + page_size + '&' +
                "output=json";
    }
}
