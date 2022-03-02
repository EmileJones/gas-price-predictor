package com.ruoyi.common.amap.model.place;

/**
 * 搜索POI信息列表
 * @author klenkiven
 */
public class POI {

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 省
     */
    private String pname;

    /**
     * 市
     */
    private String cityname;

    /**
     * 区（县）
     */
    private String adname;

    /**
     * 经纬度
     */
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
