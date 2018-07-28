package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

public class SjForm extends ABaseDO {
    private static final long serialVersionUID = 1L;

    // 编码
    private String code;

    // 申请人
    private String applyUser;

    // 申请等级
    private Integer applyLevel;

    // 申请时间
    private Date applyDatetime;

    // 团队人数
    private String reNumber;

    // 打款金额
    private Long payAmount;

    // 打款截图
    private String payPdf;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 授权书时间
    private Date impowerDatetime;

    // 状态
    private String status;

    // 备注
    private String remark;

    /***************** DB **************************/

    private BUser user;

    private Long impowerAmount;

    private Date applyDatetimeStart;

    private Date applyDatetimeEnd;

    private Date approveDatetimeStart;

    private Date approveDatetimeEnd;

    private String userIdForQuery;

    private Date impowerDatetimeStart;

    private Date impowerDatetimeEnd;

    private String keyWord;

    /*******************************************/

    // 获取编码
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 获取申请人
    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    // 获取申请等级
    public Integer getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(Integer applyLevel) {
        this.applyLevel = applyLevel;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    // 获取申请等级
    public String getReNumber() {
        return reNumber;
    }

    public void setReNumber(String reNumber) {
        this.reNumber = reNumber;
    }

    // 获取付款金额
    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    // 获取付款截图
    public String getPaymentPdf() {
        return payPdf;
    }

    public void setPaymentPdf(String paymentPdf) {
        this.payPdf = paymentPdf;
    }

    // 获取审核人
    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    // 获取审核时间
    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    // 获取授权书时间
    public Date getImpowerDatetime() {
        return impowerDatetime;
    }

    public void setImpowerDatetime(Date impowerDatetime) {
        this.impowerDatetime = impowerDatetime;
    }

    // 获取备注
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // 获取状态
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getImpowerDatetimeStart() {
        return impowerDatetimeStart;
    }

    public void setImpowerDatetimeStart(Date impowerDatetimeStart) {
        this.impowerDatetimeStart = impowerDatetimeStart;
    }

    public Date getImpowerDatetimeEnd() {
        return impowerDatetimeEnd;
    }

    public void setImpowerDatetimeEnd(Date impowerDatetimeEnd) {
        this.impowerDatetimeEnd = impowerDatetimeEnd;
    }

}
