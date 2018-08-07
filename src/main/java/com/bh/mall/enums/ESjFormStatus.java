package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESjFormStatus {

    APPROVE_SJ("0", "上级审核升级"), COMPANY_APPROVE("1", "公司审核升级"), NO_THROGH("2",
            "升级未通过"), APPROVED("3", "已升级");

    ESjFormStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ESjFormStatus> getMap() {
        Map<String, ESjFormStatus> map = new HashMap<String, ESjFormStatus>();
        for (ESjFormStatus userStatus : ESjFormStatus.values()) {
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
