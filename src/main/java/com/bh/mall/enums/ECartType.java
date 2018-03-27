package com.bh.mall.enums;

public enum ECartType {

    ReduceQuantity("0", "减少数量"), ADDQuantity("1", "增加数量");
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
