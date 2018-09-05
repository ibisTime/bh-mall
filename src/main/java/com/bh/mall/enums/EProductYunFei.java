package com.bh.mall.enums;

public enum EProductYunFei {
    YunFei_NO("0", "不包邮"), YunFei_YES("1", "不包邮");

    EProductYunFei(String code, String value) {
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
