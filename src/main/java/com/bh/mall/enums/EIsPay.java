package com.bh.mall.enums;

public enum EIsPay {

    PAY_NO("0", "出货奖励未发放"), PAY_YES("1", "出货奖励已发放");

    EIsPay(String code, String value) {
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
