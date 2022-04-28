package com.ruoyi.gas.module.price.domain;

public class OpponentMessage {
    private static final int INVALID_FLAG = 0;
    private static final int EFFECTIVE_FLAG = 1;

    /**
     * 数据唯一标识
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 系统外加油站ID
     */
    private String outGasStationId;

    /**
     * 系统外加油站别名
     */
    private String outGasStationName;

    /**
     * 此信息状态
     */
    private int status = EFFECTIVE_FLAG;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
