package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 意向收集表
 * api接口： 
 * 627250 ：申请代理 （无推荐人）
 * @author: clockorange 
 * @since: Jul 5, 2018 8:25:10 PM 
 * @history:
 */

public class AgentAllot extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 申请人
    private String applyUser;

    // 申请等级
    private Integer applyLevel;

    // 意向归属人名字
    private String toUserId;

    // 意向归属人团队
    private String toTeamName;

    // 意向归属人等级
    private Integer toLevel;

    // 意向归属人电话
    private String toUserMobile;

    // 打款截图
    private String payPdf;

    // 是否打款
    private boolean result;

    // 审核人
    private String manager;

    // 申请时间
    private Date applyDatetime;

    // 审核时间
    private Date approveDatetime;

    // 状态
    private String status;

    // 备注
    private String remark;

    /***************** DB **************************/

    private BUser user;

    // 当前等级
    private Integer level;

    private Long impowerAmount;

    private Date applyDatetimeStart;

    private Date applyDatetimeEnd;

    private Date approveDatetimeStart;

    private Date approveDatetimeEnd;

    private String userIdForQuery;

    private String keyWord;

    /***********************************/

    // 获取编号
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(Integer applyLevel) {
        this.applyLevel = applyLevel;
    }

    // 获取意向归属人信息
    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getToUserName() {
        return toUserId;
    }

    public String getToUserMobile() {
        return toUserMobile;
    }

    public void setToUserName(String toUserName) {
        this.toUserId = toUserName;
    }

    public void setToUserMobile(String toUserMobile) {
        this.toUserMobile = toUserMobile;
    }

    public String getToTeamName() {
        return toTeamName;
    }

    public Integer getToLevel() {
        return toLevel;
    }

    public void setToTeamName(String toTeamName) {
        this.toTeamName = toTeamName;
    }

    public void setToLevel(Integer toLevel) {
        this.toLevel = toLevel;
    }

    // 获取打款截图
    public String getPayPdf() {
        return payPdf;
    }

    public void setPayPdf(String payPdf) {
        this.payPdf = payPdf;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    // 获取审核人
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    // 获取申请时间
    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    // 状态
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 备注
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    // 获取数据库 buser
    public BUser getUser() {
        return user;
    }

    public void setUser(BUser user) {
        this.user = user;
    }

    // 获取升级金额
    public Long getImpowerAmount() {
        return impowerAmount;
    }

    public void setImpowerAmount(Long impowerAmount) {
        this.impowerAmount = impowerAmount;
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

    public Date getApproveDatetimeStart() {
        return approveDatetimeStart;
    }

    public void setApproveDatetimeStart(Date approveDatetimeStart) {
        this.approveDatetimeStart = approveDatetimeStart;
    }

    public Date getApproveDatetimeEnd() {
        return approveDatetimeEnd;
    }

    public void setApproveDatetimeEnd(Date approveDatetimeEnd) {
        this.approveDatetimeEnd = approveDatetimeEnd;
    }

    public String getUserIdForQuery() {
        return userIdForQuery;
    }

    public void setUserIdForQuery(String userIdForQuery) {
        this.userIdForQuery = userIdForQuery;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
