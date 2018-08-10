package com.bh.mall.enums;

public enum EAgentLogStatus {

    MIND("0", "有意向"), ALLOTED("1", "已分配"), ACCEPT("2", "接受意向"), IGNORED("3",
            "已忽略"), IMPOWERO_INFO("4", "待填写授权资料"), ADD_INFO("5",
                    "补充授权资料"), TO_APPROVE("6", "待上级授权"),

    COMPANY_IMPOWER("7", "待公司授权"), IMPOWERED("8", "已授权"), CANCELED("9",
            "未授权"), TO_CANCEL("10", "退出待上级审核"), CANCEL_COMPANY("11", "退出待公司审核"),

    TO_UPGRADE("12", "升级待上级审核"), UPGRADE_COMPANY("13",
            "升级待公司审核"), THROUGH_YES("14", "已升级"), THROUGH_NO("15", "升级未通过");

    EAgentLogStatus(String code, String value) {
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
