package com.bh.mall.enums;

public enum ECodeStatus {

    TO_USER("0", "待下载"), USE_NO("1", "未使用"), USE_YES("2",
            "已使用"), SPLIT_SINGLE("3", "已拆分");

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
