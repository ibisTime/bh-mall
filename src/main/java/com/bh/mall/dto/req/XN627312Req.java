package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 设置角色
 * @author: nyc 
 * @since: 2018年4月26日 下午5:54:31 
 * @history:
 */
public class XN627312Req {

    // （必填）手机号
    @NotBlank(message = "不能为空")
    private String mobile;

    // （必填）登录密码
    @NotBlank(message = "不能为空")
    private String loginPwd;

    // （必填）真实姓名
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
