package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESqFormStatus {

    ADD_INFO("3", "补充授权资料"), TO_APPROVE("4", "待上级审核授权"), COMPANY_IMPOWER("5",
            "授权待公司审核"), IMPOWERED("6", "已授权"), CANCELED("7", "授权未通过"),

    TO_CANCEL("8", "申请退出待上级审核"), CANCEL_COMPANY("9", "申请退出待公司审核");

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
