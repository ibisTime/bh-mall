package com.bh.mall.enums;

public enum EIsImpower {

    NO_Impwoer("0", "未完成授权单"), NO_Upgrade("1", "未完成升级单"), Normal("2",
            "正常"), NO_CHARGE("3", "未充值");

    EIsImpower(String code, String value) {
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
