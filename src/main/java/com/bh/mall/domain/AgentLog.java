package com.bh.mall.domain;

import java.util.Date;

public class AgentLog {

    // 编号
    private String code;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 当前等级
    private String level;

    // 申请等级
    private String applyLevel;

    // 上级
    private String superior;

    // 推荐人
    private String userReferee;

    // 团队名称
    private String teamName;

    // 打款截图
    private String paymentPdf;

    // 保证金截图
    private String marginPdf;

    // 状态
    private String status;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(String applyLevel) {
        this.applyLevel = applyLevel;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPaymentPdf() {
        return paymentPdf;
    }

    public void setPaymentPdf(String paymentPdf) {
        this.paymentPdf = paymentPdf;
    }

    public String getMarginPdf() {
        return marginPdf;
    }

    public void setMarginPdf(String marginPdf) {
        this.marginPdf = marginPdf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }
}
