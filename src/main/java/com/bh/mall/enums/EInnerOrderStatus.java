package com.bh.mall.enums;

public enum EInnerOrderStatus {

    toPay("0", "待支付"), Paid("1", "已支付审单"), TO_SEND("2", "待发货"), TO_Deliver("3",
            "待收货"), Delivered("4", "已收货"), TO_Cancel("5",
                    "申请取消"), Canceled("6", "已取消"), Pay_No("7", "支付失败");

    EInnerOrderStatus(String code, String value) {
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
