package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 检查登录密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:31:23 
 * @history:
 */
public class XN627305Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 登录密码
    @NotBlank
    private String loginPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

}
