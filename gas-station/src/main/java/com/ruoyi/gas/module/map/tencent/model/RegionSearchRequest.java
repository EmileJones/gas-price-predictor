package com.ruoyi.gas.module.map.tencent.model;

import cn.hutool.core.util.URLUtil;

/**
 * 行政区域搜索
 * @author KlenKiven
 * @email wzl709@outlook.com
 */
public class RegionSearchRequest {

    // 1 [默认] 若当前城市搜索无结果，则自动扩大范围
    public static final int AUTO_EXTEND = 1;

    private String key;

    private String keyword;

    private String adcode;

    /** 过滤策略：搜索目标为加油站 */
    private final String filter = "category=中石化,中石油,其它加油站";

    private final int pageSize = 20;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    @Override
    public String toString() {
        return "?key=" + key + '&' +
                "keyword=" + keyword + '&' +
                "boundary=" + "region(" + adcode + "," + AUTO_EXTEND + ")" + '&' +
                "filter=" + filter + '&' +
                "page_size=" + pageSize;
    }
}
