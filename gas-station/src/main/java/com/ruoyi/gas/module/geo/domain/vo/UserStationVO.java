package com.ruoyi.gas.module.geo.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用户油站VO
 *
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public class UserStationVO {

    private String stationId;

    private String stationName;

    private Integer status;

    /** 加油站经营数据最新更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserStationVO{" +
                "stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                '}';
    }
}
