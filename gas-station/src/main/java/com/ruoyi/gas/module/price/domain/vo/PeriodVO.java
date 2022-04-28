package com.ruoyi.gas.module.price.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 周期的前端展示类
 * @author KlenKiven
 */
public class PeriodVO {

    private Integer id;

    /** 价格是否上调 */
    private Boolean rise;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 备注 */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getRise() {
        return rise;
    }

    public void setRise(Boolean rise) {
        this.rise = rise;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
