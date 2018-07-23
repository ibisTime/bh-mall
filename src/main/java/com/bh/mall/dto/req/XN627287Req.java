package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * C-user reset mobile
 * @author: clockorange 
 * @since: Jul 17, 2018 1:13:25 PM 
 * @history:
 */

public class XN627287Req {
    // （必填）用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （必填）新手机号
    @NotBlank(message = "新手机号不能为空")
    private String newMobile;

    // (必填) 新手机号验证码
    @NotBlank(message = "新手机号验证码不能为空")
    private String smsCaptcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
