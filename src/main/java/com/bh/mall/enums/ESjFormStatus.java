package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESjFormStatus {

    TO_UPGRADE("12", "上级审核升级"), UPGRADE_COMPANY("13",
            "公司审核升级"), THROUGH_YES("14", "已升级"), IMPOWERED("15", "升级未通过");

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
