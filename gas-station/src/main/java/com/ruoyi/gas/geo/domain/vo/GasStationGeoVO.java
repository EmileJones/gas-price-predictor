package com.ruoyi.gas.geo.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.gas.utils.DoubleSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 前端展示页面表格
 * @author KlenKiven
 */
public class GasStationGeoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 地理信息主键 */
    private Long id;

    /** 系统内加油站ID */
    @Excel(name = "系统内加油站ID")
    private String systemStationId;

    /** 系统内加油站名称 */
    @Excel(name = "系统内加油站名称")
    private String systemStationName;

    /** 系统外加油站ID */
    @Excel(name = "系统外加油站ID")
    private String outSystemStationId;

    /** 系统外加油站名称 */
    @Excel(name = "系统外加油站名称")
    private String outSystemStationName;

    /** 两加油站之间的距离 */
    @Excel(name = "两加油站之间的距离")
    private Double distance;

    /** 两加油站之间路线红绿灯数 */
    @Excel(name = "两加油站之间路线红绿灯数")
    private Integer trafficLights;

    /** 两加油站红绿灯数的影响因子 */
    @Excel(name = "两加油站红绿灯数的影响因子")
    private Double trafficFactor;

    /** 路线形状 */
    @Excel(name = "路线形状")
    private String routeShape;

    /** 路线曲折度影响系数 */
    @Excel(name = "路线曲折度影响系数")
    @JsonSerialize(using = DoubleSerializer.class)
    private Double routeShapeFactor;

    /** 路线影响系数 */
    @Excel(name = "路线影响系数")
    @JsonSerialize(using = DoubleSerializer.class)
    private Double routeFactor;

    public String getSystemStationName() {
        return systemStationName;
    }

    public void setSystemStationName(String systemStationName) {
        this.systemStationName = systemStationName;
    }

    public String getOutSystemStationName() {
        return outSystemStationName;
    }

    public void setOutSystemStationName(String outSystemStationName) {
        this.outSystemStationName = outSystemStationName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSystemStationId(String systemStationId)
    {
        this.systemStationId = systemStationId;
    }

    public String getSystemStationId()
    {
        return systemStationId;
    }
    public void setOutSystemStationId(String outSystemStationId)
    {
        this.outSystemStationId = outSystemStationId;
    }

    public String getOutSystemStationId()
    {
        return outSystemStationId;
    }
    public void setDistance(Double distance)
    {
        this.distance = distance;
    }

    public Double getDistance()
    {
        return distance;
    }
    public void setTrafficLights(Integer trafficLights)
    {
        this.trafficLights = trafficLights;
    }

    public Integer getTrafficLights()
    {
        return trafficLights;
    }
    public void setTrafficFactor(Double trafficFactor)
    {
        this.trafficFactor = trafficFactor;
    }

    public Double getTrafficFactor()
    {
        return trafficFactor;
    }
    public void setRouteShape(String routeShape)
    {
        this.routeShape = routeShape;
    }

    public String getRouteShape()
    {
        return routeShape;
    }
    public void setRouteShapeFactor(Double routeShapeFactor)
    {
        this.routeShapeFactor = routeShapeFactor;
    }

    public Double getRouteShapeFactor()
    {
        return routeShapeFactor;
    }
    public void setRouteFactor(Double routeFactor)
    {
        this.routeFactor = routeFactor;
    }

    public Double getRouteFactor()
    {
        return routeFactor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("systemStationId", getSystemStationId())
                .append("systemStationName", getSystemStationName())
                .append("outSystemStationId", getOutSystemStationId())
                .append("outSystemStationName", getOutSystemStationName())
                .append("distance", getDistance())
                .append("trafficLights", getTrafficLights())
                .append("trafficFactor", getTrafficFactor())
                .append("routeShape", getRouteShape())
                .append("routeShapeFactor", getRouteShapeFactor())
                .append("routeFactor", getRouteFactor())
                .toString();
    }

}
