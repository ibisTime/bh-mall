package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 重置登录密码
 * @author: nyc 
 * @since: 2018年4月26日 下午5:54:31 
 * @history:
 */
public class XN627115Req {

    // （必填） 手机号
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    // (必填) 验证码
    @NotBlank(message = "验证码不能为空")
    private String smsCaptcha;

    // （必填） 新密码
    @NotBlank(message = "新密码不能为空")
    private String newLoginPwd;

    public String getMobile() {
        return mobile;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

}
