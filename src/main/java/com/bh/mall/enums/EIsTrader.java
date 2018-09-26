package com.bh.mall.enums;

public enum EIsTrader {

    TRADER_NO("0", "不是操盘手"), TRADER_YES("1", "是操盘手");

    EIsTrader(String code, String value) {
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
