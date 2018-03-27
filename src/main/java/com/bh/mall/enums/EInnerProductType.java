package com.bh.mall.enums;

public enum EInnerProductType {

    REDUCE("0", "减少"), ADD("1", "增加");

    EInnerProductType(String code, String value) {
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
