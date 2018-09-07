package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESqFormStatus {

    TO_APPROVE("6", "待上级审核授权"), COMPANY_IMPOWER("7", "授权待公司审核"), IMPOWERED("8",
            "已授权"),

    CANCELED("9", "授权未通过"), TO_CANCEL("10", "申请退出待上级审核"), CANCEL_COMPANY("11",
            "申请退出待公司审核");

    ESqFormStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ESqFormStatus> getMap() {
        Map<String, ESqFormStatus> map = new HashMap<String, ESqFormStatus>();
        for (ESqFormStatus userStatus : ESqFormStatus.values()) {
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
