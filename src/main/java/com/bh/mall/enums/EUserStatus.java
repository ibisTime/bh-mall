package com.bh.mall.enums;

import java.util.HashMap;
import java.util.Map;

public enum EUserStatus {
    NORMAL("0", "正常"), Li_Locked("1", "程序锁定"), Ren_Locked("2", "人工锁定"),


    TO_WILL("3", "有意愿"), IGNORED("4", "已忽略"), ALLOTED("5", "已分配"), TO_APPROVE(
        "6", "待审核"), IMPOWERED("7", "已授权"), TO_CANCEL("8", "申请取消授权"), CANCELED(
        "9", "授权已取消"), NO_THROUGH("10", "审核未通过"), TO_COMPANYAPPROVE("11",
        "待公司审核"), TO_UPGRADE("12", "申请升级"), UPGRADED("13", "已升级"), TO_Company_Upgrade(
                                                            "14", "升级待公司审核");

    EUserStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EUserStatus> getMap() {
        Map<String, EUserStatus> map = new HashMap<String, EUserStatus>();
        for (EUserStatus userStatus : EUserStatus.values()) {
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
