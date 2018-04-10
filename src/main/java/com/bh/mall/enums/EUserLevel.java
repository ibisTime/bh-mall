package com.bh.mall.enums;

/**
 * 用户等级
 * @author: xieyj 
 * @since: 2016年5月24日 上午9:11:47 
 * @history:
 */
public enum EUserLevel {
    ONE("1", "合伙人"), TWO("2", "联创"), THREE("3", "官方"), FOUR("4",
            "总代"), FIVE("5", "特约");

    EUserLevel(String code, String value) {
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
