package com.bh.mall.enums;

public enum EAgencyType {

    Allot("0", "意向分配"), Imporder("1", "授权"), Upgrade("2", "升级");

    EAgencyType(String code, String value) {
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
