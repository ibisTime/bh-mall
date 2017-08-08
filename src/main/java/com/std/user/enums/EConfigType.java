package com.std.user.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum EConfigType {
    HX("HX", "环信"), QINIU("QINIU", "七牛"), WEIXIN_H5("WX_H5", "微信h5"), WEIXIN_APP(
            "WX_APP", "微信APP");

    EConfigType(String code, String value) {
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
