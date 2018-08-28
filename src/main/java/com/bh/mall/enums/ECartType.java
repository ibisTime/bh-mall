package com.bh.mall.enums;

public enum ECartType {

    AGENT("B", "代理"), CUSER("C", "用户");
    ECartType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
