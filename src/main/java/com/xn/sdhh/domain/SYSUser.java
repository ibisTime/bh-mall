
package com.xn.sdhh.domain;

import java.util.Date;
import java.util.List;

import com.xn.sdhh.dao.base.ABaseDO;

/**
 * 平台端用户
 * @author: clockorange 
 * @since: Jul 5, 2018 8:28:27 PM 
 * @history:
 */
public class SYSUser extends ABaseDO {

    private static final long serialVersionUID = -332310384678966884L;

    // 管理人员id
    private String userId;

    // 角色编号
    private String roleCode;

    // 真实姓名
    private String realName;

    // 头像
    private String photo;

    // 电话
    private String mobile;

    // 登陆名
    private String loginName;

    // 登陆密码
    private String loginPwd;

    // 登录密码强度
    private String loginPwdStrength;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 系统编号
    private String systemCode;

    // 备注
    private String remark;

    // *****************DB****************

    // 系统用户列表
    private List<SYSUser> sysUserList;

    // 注册时间起
    private Date createDatetimeStart;

    // 注册时间止
    private Date createDatetimeEnd;

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

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setKind(String realName) {
        this.realName = realName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 登陆密码强度
    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

}
