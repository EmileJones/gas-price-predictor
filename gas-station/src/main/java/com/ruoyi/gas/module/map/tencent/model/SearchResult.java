/**
  * Copyright 2022 bejson.com 
  */
package com.ruoyi.gas.module.map.tencent.model;
import com.ruoyi.gas.module.map.tencent.model.search.Data;
import com.ruoyi.gas.module.map.tencent.model.search.Region;

import java.util.List;

/**
 * Auto-generated: 2022-04-24 9:46:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SearchResult {

    private int status;
    private String message;
    private int count;
    private String request_id;
    private List<Data> data;
    private Region region;
    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setRequest_id(String request_id) {
         this.request_id = request_id;
     }
     public String getRequest_id() {
         return request_id;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setRegion(Region region) {
         this.region = region;
     }
     public Region getRegion() {
         return region;
     }

}