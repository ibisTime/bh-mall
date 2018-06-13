package com.bh.mall.enums;

public enum ECheckStatus {

    RED_LOW("0", "不通过"), MIN_LOW("1", "通过"), NORMAL("3", "正常");

    ECheckStatus(String code, String value) {
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
