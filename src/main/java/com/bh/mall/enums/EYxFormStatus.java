package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum EYxFormStatus {

    MIND("0", "有意愿"), IGNORED("1", "已忽略"), ACCEPT("2", "已接受"), ALLOTED("3",
            "已分配");

    EYxFormStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EYxFormStatus> getMap() {
        Map<String, EYxFormStatus> map = new HashMap<String, EYxFormStatus>();
        for (EYxFormStatus userStatus : EYxFormStatus.values()) {
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
