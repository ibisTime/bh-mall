package com.std.user.dto.req;

public class CD799002Req {

    // 验证码编号-- 必填
    private String code;

    // 接受短信的手机号--必填
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
