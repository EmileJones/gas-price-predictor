package com.ruoyi.gas.module.map.tencent.model;

/**
 * 路线规划请求
 * @author KlenKiven
 */
public class DrivingRequest {
    private String key;

    private String from;

    private String to;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "?key=" + key + "&" +
                "from=" + from + "&" +
                "to=" + to;
    }
}
