package com.bh.mall.enums;

public enum EMaterialStatus {
    NotShow("0", "不显示在列表"), IsShow("1", "显示在列表");
    EMaterialStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
