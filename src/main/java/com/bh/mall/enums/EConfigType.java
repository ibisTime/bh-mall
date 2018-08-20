package com.bh.mall.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum EConfigType {
    HX("hx", "环信"), QINIU("qiniu", "七牛"), WEIXIN_H5("wx_h5",
            "微信h5"), WEIXIN_APP("wx_app", "微信APP"),

    WEIXIN_XCX("wx_xcx", "微信小程序"), WX_PAY("wx_pay",
            "微信H5支付"), XCX_PAY("xcx_pay", "小程序支付");

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
