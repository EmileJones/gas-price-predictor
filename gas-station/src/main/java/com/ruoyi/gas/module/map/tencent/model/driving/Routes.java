/**
  * Copyright 2022 bejson.com 
  */
package com.ruoyi.gas.module.map.tencent.model.driving;
import java.util.List;

/**
 * Auto-generated: 2022-04-24 10:14:25
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Routes {

    private String mode;
    private long distance;
    private int duration;
    private int traffic_light_count;
    private Restriction restriction;
    private List<Steps> steps;
    public void setMode(String mode) {
         this.mode = mode;
     }
     public String getMode() {
         return mode;
     }

    public void setDistance(long distance) {
         this.distance = distance;
     }
     public long getDistance() {
         return distance;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setTraffic_light_count(int traffic_light_count) {
         this.traffic_light_count = traffic_light_count;
     }
     public int getTraffic_light_count() {
         return traffic_light_count;
     }

    public void setRestriction(Restriction restriction) {
         this.restriction = restriction;
     }
     public Restriction getRestriction() {
         return restriction;
     }

    public void setSteps(List<Steps> steps) {
         this.steps = steps;
     }
     public List<Steps> getSteps() {
         return steps;
     }

}