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
public class Result {

    private String restriction;
    private int total;
    private String session_id;
    private List<Routes> routes;
    private String holiday;
    public void setRestriction(String restriction) {
         this.restriction = restriction;
     }
     public String getRestriction() {
         return restriction;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setSession_id(String session_id) {
         this.session_id = session_id;
     }
     public String getSession_id() {
         return session_id;
     }

    public void setRoutes(List<Routes> routes) {
         this.routes = routes;
     }
     public List<Routes> getRoutes() {
         return routes;
     }

    public void setHoliday(String holiday) {
         this.holiday = holiday;
     }
     public String getHoliday() {
         return holiday;
     }

}