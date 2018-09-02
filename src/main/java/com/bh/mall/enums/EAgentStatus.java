package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAgentStatus {

    MIND("0", "有意向"), IGNORED("1", "已忽略"), ALLOTED("3", "已分配"), IMPOWERO_INFO(
            "4", "待填写授权资料"), ADD_INFO("5", "补充授权资料"), TO_APPROVE("6", "待上级授权"),

    COMPANY_IMPOWER("7", "待公司授权"), IMPOWERED("8", "已授权"), CANCELED("9",
            "授权未通过"),

    TO_CANCEL("10", "退出待上级审核"), CANCEL_COMPANY("11", "退出待公司审核"), TO_UPGRADE(
            "12", "升级待上级审核"), UPGRADE_COMPANY("13",
                    "升级待公司审核"), THROUGH_YES("14", "已升级");

    EAgentStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EAgentStatus> getMap() {
        Map<String, EAgentStatus> map = new HashMap<String, EAgentStatus>();
        for (EAgentStatus userStatus : EAgentStatus.values()) {
            map.put(userStatus.getCode(), userStatus);
        }
        return map;
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
