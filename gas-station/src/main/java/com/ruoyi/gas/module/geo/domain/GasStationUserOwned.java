package com.ruoyi.gas.module.geo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户加油站对象 gas_station_user_owned
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 */
public class GasStationUserOwned extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 加油站ID（系统内） */
    @Excel(name = "加油站ID")
    private String stationId;

    /** 加油站名称 */
    @Excel(name = "加油站名称")
    private String stationName;

    /** 加油站状态（0:创建,1:正常,2:禁用,3:审核中） */
    @Excel(name = "加油站状态")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStationId(String stationId) 
    {
        this.stationId = stationId;
    }

    public String getStationId() 
    {
        return stationId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("stationId", getStationId())
            .append("stationName", getStationName())
            .append("status", getStatus())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
