package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum EAgentStatus {

    MIND("0", "有意向"), IGNORED("1", "已忽略"), IMPOWERO_INFO("2",
            "待填写授权资料"), ADD_INFO("3", "补充授权资料"), TO_APPROVE("4",
                    "待审核授权"), COMPANY_IMPOWER("5", "待公司授权"), IMPOWERED("5",
                            "已授权"), UPGRADE("6", "已升级"), CANCELED("7", "未授权");

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
