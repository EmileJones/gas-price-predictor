package com.ruoyi.gas.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 加油站信息对象 gas_station_info
 * 
 * @author ruoyi
 * @date 2022-01-21
 */
public class GasStationInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一ID */
    private String id;

    /** 加油站名称 */
    @Excel(name = "加油站名称")
    private String name;

    /** 加油站位置 */
    @Excel(name = "加油站位置")
    private String location;

    /** 加油站所在省 */
    @Excel(name = "加油站所在省")
    private String province;

    /** 加油站所在市 */
    @Excel(name = "加油站所在市")
    private String city;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("location", getLocation())
            .append("province", getProvince())
            .append("city", getCity())
            .toString();
    }
}
