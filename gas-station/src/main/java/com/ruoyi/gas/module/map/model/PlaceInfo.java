package com.ruoyi.gas.module.map.model;

public class PlaceInfo {

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
     * Address
     */
    private String address;

    /**
     * 经纬度
     */
    private String location;

    /**
     * 直线距离
     */
    private Long directDistance;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getDirectDistance() {
        return directDistance;
    }

    public void setDirectDistance(Long directDistance) {
        this.directDistance = directDistance;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", pname='" + pname + '\'' +
                ", cityname='" + cityname + '\'' +
                ", adname='" + adname + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", directDistance='" + directDistance + '\'' +
                '}';
    }
}
