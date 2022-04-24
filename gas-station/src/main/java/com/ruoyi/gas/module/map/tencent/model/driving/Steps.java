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
public class Steps {

    private String instruction;
    private List<Integer> polyline_idx;
    private String road_name;
    private String dir_desc;
    private int distance;
    private int duration;
    private String act_desc;
    private String accessorial_desc;
    public void setInstruction(String instruction) {
         this.instruction = instruction;
     }
     public String getInstruction() {
         return instruction;
     }

    public void setPolyline_idx(List<Integer> polyline_idx) {
         this.polyline_idx = polyline_idx;
     }
     public List<Integer> getPolyline_idx() {
         return polyline_idx;
     }

    public void setRoad_name(String road_name) {
         this.road_name = road_name;
     }
     public String getRoad_name() {
         return road_name;
     }

    public void setDir_desc(String dir_desc) {
         this.dir_desc = dir_desc;
     }
     public String getDir_desc() {
         return dir_desc;
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

    public void setAct_desc(String act_desc) {
         this.act_desc = act_desc;
     }
     public String getAct_desc() {
         return act_desc;
     }

    public void setAccessorial_desc(String accessorial_desc) {
         this.accessorial_desc = accessorial_desc;
     }
     public String getAccessorial_desc() {
         return accessorial_desc;
     }

}