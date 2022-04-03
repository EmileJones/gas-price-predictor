package com.ruoyi.gas.module.map.baidu.model;

/**
 * 百度行政区域搜索请求
 * @author KlenKiven
 */
public class RegionSearchRequest {

    private String ak;

    /** 搜索关键字 */
    private String query;

    /** 搜索地点的类型 */
    private final String tag = "加油站";

    /** 搜索位置 */
    private String region;

    /** 只搜索某个行政区域内的目标 */
    private final Boolean city_limit = false;

    /** 页面大小为20条 */
    private final Integer page_size = 20;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTag() {
        return tag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
                "query=" + query + "," + tag + '&' +
                "region=" + region + '&' +
                "city_limit=" + city_limit + '&' +
                "page_size=" + page_size +
                "&output=json";
    }
}
