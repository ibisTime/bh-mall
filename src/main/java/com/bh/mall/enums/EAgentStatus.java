package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAgentStatus {

    MIND("0", "有意愿"), IGNORE("1", "已忽略"), TO_APPROVE("2",
            "待审核授权"), IMPOWERO_INFO("3", "待填写授权资料"), ADD_INFO("4",
                    "补充授权资料"), IMPOWERED("5", "已授权"), NO_THROUGH("6", "未授权");

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
