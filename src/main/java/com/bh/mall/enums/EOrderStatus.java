package com.bh.mall.enums;

public enum EOrderStatus {

    Unpaid("0", "待支付"), Paid("1", "已支付待审核"), TO_Apprvoe("2",
            "已审单待发货"), TO_Deliver("3", "待收货"), Received("4",
                    "已收货"), TO_Cancel("5", "申请取消"), Canceled("6",
                            "已取消"), NO_CallOFF("(1,2,3,5)", "无法取消代理授权");

    EOrderStatus(String code, String value) {
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
