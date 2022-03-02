package com.ruoyi.common.amap.model;

import com.ruoyi.common.amap.model.driving.Route;

/**
 * 驾车路线规划 -- Result
 * @author klenkiven
 */
public class DrivingDirectionResult {

    /**
     * 本次API访问状态，如果成功返回1，如果失败返回0。
     */
    private String status;

    /**
     * 访问状态值的说明，如果成功返回"ok"，失败返回错误原因，具体见错误码说明。
     */
    private String info;

    /**
     * 返回状态说明,10000代表正确,详情参阅info状态表
     */
    private String infocode;

    /**
     * 路径规划方案总数
     */
    private String count;

    /**
     * 返回的规划方案列表
     */
    private Route route;

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
    public String getInfocode() {
        return infocode;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public String getCount() {
        return count;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
    public Route getRoute() {
        return route;
    }

}
