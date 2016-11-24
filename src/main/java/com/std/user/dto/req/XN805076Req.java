package com.std.user.dto.req;

public class XN805076Req {
    // 手机号（必填）
    private String mobile;

    // 注册密码（必填）
    private String loginPwd;

    // 注册密码（必填）
    private String loginPwdStrength;

    // 推荐人（非必填）
    private String userReferee;

    // 手机验证码（必填）
    private String smsCaptcha;

    // 公司编号（选填）
    private String companyCode;

    // 是否区分不同商户（选填 1 区分，其他不区分）
    private String isMall;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
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

    public String getIsMall() {
        return isMall;
    }

    public void setIsMall(String isMall) {
        this.isMall = isMall;
    }
}
