package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627340Req {

    // 开放编号（必填）
    @NotBlank(message = "微信开放编号不能为空")
    private String code;

    // 昵称
    private String nickname;

    // 头像
    private String photo;

    // 推荐人
    private String userReferee;

    // 是否强制绑定手机号（必填）
    private String isNeedMobile;

    // 手机号（选填）
    private String mobile;

    // 短信验证码（选填）
    private String smsCaptcha;

    // 是否(非微信登录)登录状态，是则无需验证手机号，否则传验证验证手机
    private String isLoginStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIsNeedMobile() {
        return isNeedMobile;
    }

    public void setIsNeedMobile(String isNeedMobile) {
        this.isNeedMobile = isNeedMobile;
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

    public String getIsLoginStatus() {
        return isLoginStatus;
    }

    public void setIsLoginStatus(String isLoginStatus) {
        this.isLoginStatus = isLoginStatus;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

}
