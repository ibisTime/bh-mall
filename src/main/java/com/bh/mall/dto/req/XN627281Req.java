package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统用户重置密码
 * @author: clockorange 
 * @since: Jul 17, 2018 11:51:45 AM 
 * @history:
 */
public class XN627281Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 新登录密码
    @NotBlank
    private String newLoginPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

}
