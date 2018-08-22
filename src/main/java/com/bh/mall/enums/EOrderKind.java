package com.bh.mall.enums;

public enum EOrderKind {

    OUT_Order("0", "出货订单"), INNER_Order("1", "内购产品订单");

    EOrderKind(String code, String value) {
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
