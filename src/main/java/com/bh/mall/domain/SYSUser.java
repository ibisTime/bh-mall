
package com.bh.mall.domain;

import java.util.List;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 平台端用户
 * @author: clockorange 
 * @since: Jul 5, 2018 8:28:27 PM 
 * @history:
 */
public class SYSUser extends ABaseDO {

    private static final long serialVersionUID = -332310384678966884L;

    /****** key ******/
    // 管理人员id
    private String userId;

    /****** 登陆信息 ******/
    // 登陆名
    private String loginName;

    // 登陆密码
    private String loginPwd;

    private String loginPwdStrength;

    //
    private String kind;

    //
    private String mobile;

    // 头像
    private String photo;

    // 公司编号
    private String companyCode;

    // 系统编号
    private String systemCode;

    // DB
    private List<SYSUser> sysUserList;

    /*****************************/

    public List<SYSUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SYSUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    // 获取id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // 获取登陆信息
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // 登陆密码强度
    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

}
