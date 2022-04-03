/**
  * Copyright 2022 bejson.com 
  */
package com.ruoyi.gas.module.map.baidu.model;
import com.ruoyi.gas.module.map.baidu.model.place.Results;

import java.util.List;

/**
 * 百度区域搜索的结果
 * @author KlenKiven
 */
public class PlaceSearchResult {

    private int status;
    private String message;
    private String result_type;
    private List<Results> results;
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

    public void setResult_type(String result_type) {
         this.result_type = result_type;
     }
     public String getResult_type() {
         return result_type;
     }

    public void setResults(List<Results> results) {
         this.results = results;
     }
     public List<Results> getResults() {
         return results;
     }

}