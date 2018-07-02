package com.bh.mall.enums;

public enum ECodeStatus {

    USE_NO("0", "未使用"), USE_YES("1", "已使用");

    ECodeStatus(String code, String value) {
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
