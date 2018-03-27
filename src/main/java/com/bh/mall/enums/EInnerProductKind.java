package com.bh.mall.enums;

public enum EInnerProductKind {
    Free_NO("0", "待支付"), Free_YES("1", "已支付待发货");

    EInnerProductKind(String code, String value) {
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
