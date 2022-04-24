/**
  * Copyright 2022 bejson.com 
  */
package com.ruoyi.gas.module.map.tencent.model.search;

/**
 * Auto-generated: 2022-04-24 9:46:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String id;
    private String title;
    private String address;
    private String tel;
    private String category;
    private int type;
    private Location location;
    private double _distance;
    private Ad_info ad_info;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setAddress(String address) {
         this.address = address;
     }
     public String getAddress() {
         return address;
     }

    public void setTel(String tel) {
         this.tel = tel;
     }
     public String getTel() {
         return tel;
     }

    public void setCategory(String category) {
         this.category = category;
     }
     public String getCategory() {
         return category;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void set_distance(double _distance) {
         this._distance = _distance;
     }
     public double get_distance() {
         return _distance;
     }

    public void setAd_info(Ad_info ad_info) {
         this.ad_info = ad_info;
     }
     public Ad_info getAd_info() {
         return ad_info;
     }

}