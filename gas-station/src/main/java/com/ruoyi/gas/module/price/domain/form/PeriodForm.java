package com.ruoyi.gas.module.price.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 查询周期数据的表单数据
 *
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public class PeriodForm {

    /**
     * 周期ID
     */
    private Integer id;

    /**
     * 价格调整时间
     */
    @NotNull(message = "调整时间不可为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 日期的结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 本次上调还是下调
     */
    @NotNull(message = "是否上调不可为空")
    private Boolean rise;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getRise() {
        return rise;
    }

    public void setRise(Boolean rise) {
        this.rise = rise;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
