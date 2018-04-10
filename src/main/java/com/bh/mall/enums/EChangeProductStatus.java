package com.bh.mall.enums;

public enum EChangeProductStatus {

    TO_CHANGE("0", "申请换货"), THROUGH_YES("1", "申请通过"), THROUGH_NO("1", "申请未通过");

    EChangeProductStatus(String code, String value) {
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
