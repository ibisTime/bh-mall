package com.bh.mall.enums;

public enum ECheckStatus {

    RED_LOW("0", "低于红线"), NO_Impwoer("1", "未完成授权单"), NO_Upgrae("2",
            "未完成升级单"), MIN_LOW("3", "门槛低于限制"), To_Charge("4", "授权或升级充值"),

    NORMAL("5", "正常"), Charging("6", "充值审核中"), TO_BUY("7",
            "购买云仓"), NO_WAREHOUSE("8", "无云仓直接买货");

    ECheckStatus(String code, String value) {
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
