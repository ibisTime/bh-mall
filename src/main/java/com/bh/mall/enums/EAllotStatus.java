package com.bh.mall.enums;

public enum EAllotStatus {

    PENDING("P", "未处理"), ALLOT("A", "已分配"), IGNORE("I", "忽略"), TO_LOW("T",
            "向下分配");

    EAllotStatus(String code, String value) {
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
