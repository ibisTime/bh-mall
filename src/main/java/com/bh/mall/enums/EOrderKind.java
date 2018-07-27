package com.bh.mall.enums;

public enum EOrderKind {

    Impower_Order("0", "授权单"), Upgrade_Order("1", "升级单"), WH_Order("2",
            "购买云仓"), Pick_Up("3", "云仓提货"), Normal_Order("4", "普通单");

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
