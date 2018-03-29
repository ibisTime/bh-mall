package com.bh.mall.enums;

public enum EProductSpecsType {

    Apply_NO("0", "不允许下单"), Apply_YES("1", "允许下单");

    EProductSpecsType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
