package com.std.user.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ECPwdType {
    HX("1", "环信"), HX_FRONT("11", "环信前端"), QINIU("2", "七牛"), WEIXIN("3", "微信"), XIAOCX(
            "4", "微信小程序");

    ECPwdType(String code, String value) {
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
