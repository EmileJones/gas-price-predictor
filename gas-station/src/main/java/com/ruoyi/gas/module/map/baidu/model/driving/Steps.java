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
public class Steps {

    private int leg_index;
    private int link_num;
    private String road_name;
    private int direction;
    private int distance;
    private int duration;
    private int road_type;
    private int toll;
    private int toll_distance;
    private String adcodes;
    private List<Traffic_condition> traffic_condition;
    private String path;
    private Start_location start_location;
    private End_location end_location;
    public void setLeg_index(int leg_index) {
         this.leg_index = leg_index;
     }
     public int getLeg_index() {
         return leg_index;
     }

    public void setLink_num(int link_num) {
         this.link_num = link_num;
     }
     public int getLink_num() {
         return link_num;
     }

    public void setRoad_name(String road_name) {
         this.road_name = road_name;
     }
     public String getRoad_name() {
         return road_name;
     }

    public void setDirection(int direction) {
         this.direction = direction;
     }
     public int getDirection() {
         return direction;
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

    public void setRoad_type(int road_type) {
         this.road_type = road_type;
     }
     public int getRoad_type() {
         return road_type;
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

    public void setAdcodes(String adcodes) {
         this.adcodes = adcodes;
     }
     public String getAdcodes() {
         return adcodes;
     }

    public void setTraffic_condition(List<Traffic_condition> traffic_condition) {
         this.traffic_condition = traffic_condition;
     }
     public List<Traffic_condition> getTraffic_condition() {
         return traffic_condition;
     }

    public void setPath(String path) {
         this.path = path;
     }
     public String getPath() {
         return path;
     }

    public void setStart_location(Start_location start_location) {
         this.start_location = start_location;
     }
     public Start_location getStart_location() {
         return start_location;
     }

    public void setEnd_location(End_location end_location) {
         this.end_location = end_location;
     }
     public End_location getEnd_location() {
         return end_location;
     }

}