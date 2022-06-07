package com.ruoyi.gas.module.geo.domain;

public class OpponentMessage {
    public static final int INVALID_FLAG = 0;
    public static final int EFFECTIVE_FLAG = 1;

    /**
     * 数据唯一标识
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 系统内加油站ID
     */
    private String gasStationId;
    /**
     * 系统外加油站ID
     */
    private String outGasStationId;
    /**
     * 系统外加油站名称
     */
    private String outGasStationName;
    /**
     * 此条信息的状态
     */
    private Integer status = EFFECTIVE_FLAG;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(String gasStationId) {
        this.gasStationId = gasStationId;
    }

    public String getOutGasStationId() {
        return outGasStationId;
    }

    public void setOutGasStationId(String outGasStationId) {
        this.outGasStationId = outGasStationId;
    }

    public String getOutGasStationName() {
        return outGasStationName;
    }

    public void setOutGasStationName(String outGasStationName) {
        this.outGasStationName = outGasStationName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
