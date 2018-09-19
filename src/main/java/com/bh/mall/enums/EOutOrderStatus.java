package com.bh.mall.enums;

public enum EOutOrderStatus {

    Unpaid("0", "待支付"), TO_APPROVE("1", "已支付待审核"), TO_SEND("2",
            "已审核待发货"), TO_RECEIVE("3", "待收货"), RECEIVED("4", "已收货"), TO_CANECL(
                    "5", "申请取消"), CANCELED("6", "已取消"), PAY_FAIL("7", "支付失败");

    EOutOrderStatus(String code, String value) {
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
