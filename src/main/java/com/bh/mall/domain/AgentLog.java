package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 代理轨迹
 * @author: nyc 
 * @since: 2018年7月29日 下午7:54:05 
 * @history:
 */
public class AgentLog extends ABaseDO {

    private static final long serialVersionUID = -4496692585035501309L;

    // 编号
    private String code;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 归属人
    private String toUserId;

    // 类型
    private String type;

    // 当前等级
    private Integer level;

    // 申请等级
    private Integer applyLevel;

    // 上级
    private String highUserId;

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

    // ********************db*******************
    // 代理
    private Agent agent;

    // 推荐人名称
    private String refereeName;

    // 门槛金额
    private Long impowerAmount;

    // 审核人
    private String approvName;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(Integer applyLevel) {
        this.applyLevel = applyLevel;
    }

    public String getHighUserId() {
        return highUserId;
    }

    public void setHighUserId(String highUserId) {
        this.highUserId = highUserId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public Long getImpowerAmount() {
        return impowerAmount;
    }

    public String getApprovName() {
        return approvName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public void setImpowerAmount(Long impowerAmount) {
        this.impowerAmount = impowerAmount;
    }

    public void setApprovName(String approvName) {
        this.approvName = approvName;
    }

}
