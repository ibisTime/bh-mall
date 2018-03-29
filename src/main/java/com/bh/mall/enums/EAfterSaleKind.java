package com.bh.mall.enums;

public enum EAfterSaleKind {

    Return("0", "退货"), Change("1", "换货");

    EAfterSaleKind(String code, String value) {
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
