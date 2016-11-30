package com.std.user.dto.req;

public class XN805153Req {

    // 用户编号（必填）
    private String userId;

    // 手机号（必填）
    private String mobile;

    // 短信验证码（必填）
    private String smsCaptcha;

    // 公司编号（选填）兼容橙商户和城市网
    private String companyCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
