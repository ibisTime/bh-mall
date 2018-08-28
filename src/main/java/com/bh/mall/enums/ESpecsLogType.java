package com.bh.mall.enums;

public enum ESpecsLogType {
    Output("0", "出库"), Input("1", "入库"), Order("2", "代理下单"), ChangeProduct("3",
            "产品置换"), QX_ORDER("4", "取消订单");

    ESpecsLogType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
