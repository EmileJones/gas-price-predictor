package com.ruoyi.gas.module.geo.domain.vo;

public class OpponentMessageVO {
    /**
     * 数据唯一标识
     */
    private Long id;
    /**
     * 系统外加油站ID
     */
    private String outGasStationId;
    /**
     * 系统外加油站名称
     */
    private String outGasStationName;
    /**
     * 加油站地址
     */
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
