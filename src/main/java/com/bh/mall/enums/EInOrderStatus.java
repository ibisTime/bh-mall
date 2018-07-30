package com.bh.mall.enums;

public enum EInOrderStatus {

    Unpaid("0", "待支付"), Received("1", "已收货"), TO_Cancel("2",
            "申请取消"), Canceled("3", "已取消"), Pay_NO("4", "支付失败");

    EInOrderStatus(String code, String value) {
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
