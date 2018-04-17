package com.bh.mall.enums;

public enum EChargeStatus {
    toPay("1", "待支付"), Pay_NO("2", "支付失败"), Pay_YES("3", "支付成功"), TO_Cancel("4",
            "待审核"), Cancel_NO("5", "审核未通过"), Cancel_YES("6", "审核通过");
    EChargeStatus(String code, String value) {
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
