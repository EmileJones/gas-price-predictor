package com.ruoyi.gas.module.map.tencent.model;

import cn.hutool.core.util.URLUtil;

/**
 * 周边搜索 -- 半径搜索
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public class NearbySearchRequest {
    // 1 [默认] 若当前城市搜索无结果，则自动扩大范围
    public static final int AUTO_EXTEND = 1;

    private String key;

    private String location;

    private Integer radius;

    /** 过滤策略：搜索目标为加油站 */
    private final String filter = "category=中石化,中石油,其它加油站";

    private final int pageSize = 20;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "?key=" + key + "&" +
                "boundary=" + "nearby(" + location + "," + radius + "," + AUTO_EXTEND + ")" + '&' +
                "filter=" + filter + '&' +
                "page_size=" + pageSize;
    }
}
