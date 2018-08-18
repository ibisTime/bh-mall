package com.bh.mall.enums;

public enum EOrderReportKind {

    Impower_Order("0", "授权单"), Upgrade_Order("1", "升级单"), Pick_Up("2",
            "云仓提货"), Normal_Order("3",
                    "普通订单"), C_ORDER("4", "C端订单"), IN_ORDER("5", "云仓订单");

    EOrderReportKind(String code, String value) {
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
