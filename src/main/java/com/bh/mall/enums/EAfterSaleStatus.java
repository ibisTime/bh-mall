package com.bh.mall.enums;

public enum EAfterSaleStatus {

    TO_Approve("0", "待审核"), Approve_YES("1", "审核通"), Approve_NO("2",
            "审核不通过"), Delived("3", "已回寄发货");
    ;

    EAfterSaleStatus(String code, String value) {
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
