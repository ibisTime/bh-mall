package com.std.user.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAuthType {
    ZM_SCORE("zm_score", "芝麻分"), STUDENT("student", "学生");
    public static Map<String, EAuthType> getBooleanResultMap() {
        Map<String, EAuthType> map = new HashMap<String, EAuthType>();
        for (EAuthType status : EAuthType.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EAuthType(String code, String value) {
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
