package com.std.user.dto.req;

public class XN806016Req {

    // 登录名(必填)
    private String loginName;

    // 密码(必填)
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
