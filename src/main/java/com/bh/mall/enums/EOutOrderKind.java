package com.bh.mall.enums;

public enum EOutOrderKind {

    Impower_Order("0", "授权单"), Upgrade_Order("1", "升级单"), Pick_Up("2",
            "云仓提货"), Normal_Order("3", "C端订单");

    EOutOrderKind(String code, String value) {
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
