package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN627277Req {
    // 登陆名（必填）
    @NotBlank
    private String loginName;

    // 登陆密码（必填）
    @NotBlank
    private String loginPwd;

    // 用户类型（选填）
    // @NotBlank
    private String kind;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
