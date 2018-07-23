package com.bh.mall.enums;

/**
 * 授权 & 升级 申请的状态
 * @author: clockorange 
 * @since: Jul 11, 2018 4:49:27 PM 
 * @history:
 */
public enum EApplyStatus {

    PENDING("N", "未处理"), PASS("P", "通过"), FAIL("F", "不通过"), CANCEL("C", "取消");

    EApplyStatus(String code, String value) {
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
