package com.bh.mall.enums;

public enum EOrderKind {

    Impower_Order("0", "授权单"), Upgrade_Order("1", "升级单"), Normal_Order("2",
            "普通单");

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
