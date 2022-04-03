/**
  * Copyright 2022 bejson.com 
  */
package com.ruoyi.gas.module.map.baidu.model.place;

/**
 * Auto-generated: 2022-03-25 18:58:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Location {

    private double lat;
    private double lng;
    public void setLat(double lat) {
         this.lat = lat;
     }
     public double getLat() {
         return lat;
     }

    public void setLng(double lng) {
         this.lng = lng;
     }
     public double getLng() {
         return lng;
     }

    @Override
    public String toString() {
        return lat + "," + lng;
    }
}