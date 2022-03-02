package com.ruoyi.gas.geo.domain.form;

/**
 * 查询候选加油站信息Form表单
 * @author KlenKiven
 */
public class GasStationForm {

    /** 行政区域代码 */
    private String adcode;

    /** 查询字段 */
    private String query;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
