package com.bh.mall.enums;

public enum EAgentLogType {

    Allot("0", "有意向"), Imporder("1", "授权"), Upgrade("2", "升级"), OUT("3",
            "退出"), Update("4", "修改上级"), Trader("5", "设置操盘手");

    EAgentLogType(String code, String value) {
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
