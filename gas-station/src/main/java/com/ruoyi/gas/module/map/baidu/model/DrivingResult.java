package com.ruoyi.gas.module.map.baidu.model;

import com.ruoyi.gas.module.map.baidu.model.driving.Result;

/**
 * 百度地图路线规划结果
 *
 * @author klenkiven
 */
public class DrivingResult {

    private int status;
    private String message;
    private int type;
    private Result result;
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

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setResult(Result result) {
         this.result = result;
     }
     public Result getResult() {
         return result;
     }

}