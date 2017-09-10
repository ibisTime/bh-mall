package com.std.user.domain;

import java.util.Date;

import cn.jpush.api.report.UsersResult.User;

import com.std.user.dao.base.ABaseDO;

/**
* 认证记录
* @author: xieyj
* @since: 2017年09月10日 09:02:17
* @history:
*/
public class AuthLog extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认证类型
    private String type;

    // 认证参数1
    private String authArg1;

    // 认证参数2
    private String authArg2;

    // 认证参数3
    private String authArg3;

    // 状态
    private String status;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 审核人
    private String approveUser;

    // 审核时间
    private Date approveDatetime;

    // 结果记录
    private String result;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    // 系统编号
    private String systemCode;

    // *************db properties **********

    // 用户
    private User user;

    // 申请时间起
    private Date applyDatetimeStart;

    // 申请时间止
    private Date applyDatetimeEnd;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthArg1() {
        return authArg1;
    }

    public void setAuthArg1(String authArg1) {
        this.authArg1 = authArg1;
    }

    public String getAuthArg2() {
        return authArg2;
    }

    public void setAuthArg2(String authArg2) {
        this.authArg2 = authArg2;
    }

    public String getAuthArg3() {
        return authArg3;
    }

    public void setAuthArg3(String authArg3) {
        this.authArg3 = authArg3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
