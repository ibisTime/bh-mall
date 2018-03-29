package com.bh.mall.enums;

public enum EOrderSendType {
    Company_NO("0", "自己发货"), Company_YES("1", "公司发货");

    EOrderSendType(String code, String value) {
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
