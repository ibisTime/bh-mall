package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 设置支付密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:36:19 
 * @history:
 */
public class XN627306Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 短信验证码
    @NotBlank
    private String smsCaptcha;

    // 交易密码
    @NotBlank
    private String tradePwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }
}
