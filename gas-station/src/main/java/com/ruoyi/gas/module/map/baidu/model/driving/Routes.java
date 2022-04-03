/**
  * Copyright 2022 bejson.com 
  */
package com.ruoyi.gas.module.map.baidu.model.driving;
import java.util.List;

/**
 * Auto-generated: 2022-03-25 16:33:3
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Routes {

    private Origin origin;
    private Destination destination;
    private String tag;
    private String mrsl;
    private String route_md5;
    private int traffic_light;
    private int distance;
    private int duration;
    private int taxi_fee;
    private List<Steps> steps;
    private Restriction_info restriction_info;
    private int toll;
    private int toll_distance;
    private String route_id;
    public void setOrigin(Origin origin) {
         this.origin = origin;
     }
     public Origin getOrigin() {
         return origin;
     }

    public void setDestination(Destination destination) {
         this.destination = destination;
     }
     public Destination getDestination() {
         return destination;
     }

    public void setTag(String tag) {
         this.tag = tag;
     }
     public String getTag() {
         return tag;
     }

    public void setMrsl(String mrsl) {
         this.mrsl = mrsl;
     }
     public String getMrsl() {
         return mrsl;
     }

    public void setRoute_md5(String route_md5) {
         this.route_md5 = route_md5;
     }
     public String getRoute_md5() {
         return route_md5;
     }

    public void setTraffic_light(int traffic_light) {
         this.traffic_light = traffic_light;
     }
     public int getTraffic_light() {
         return traffic_light;
     }

    public void setDistance(int distance) {
         this.distance = distance;
     }
     public int getDistance() {
         return distance;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setTaxi_fee(int taxi_fee) {
         this.taxi_fee = taxi_fee;
     }
     public int getTaxi_fee() {
         return taxi_fee;
     }

    public void setSteps(List<Steps> steps) {
         this.steps = steps;
     }
     public List<Steps> getSteps() {
         return steps;
     }

    public void setRestriction_info(Restriction_info restriction_info) {
         this.restriction_info = restriction_info;
     }
     public Restriction_info getRestriction_info() {
         return restriction_info;
     }

    public void setToll(int toll) {
         this.toll = toll;
     }
     public int getToll() {
         return toll;
     }

    public void setToll_distance(int toll_distance) {
         this.toll_distance = toll_distance;
     }
     public int getToll_distance() {
         return toll_distance;
     }

    public void setRoute_id(String route_id) {
         this.route_id = route_id;
     }
     public String getRoute_id() {
         return route_id;
     }

}