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

    // 操作类型
    private String type;

    // 申请人
    private String applyUser;

    // 申请人姓名 +++++
    private String realName;

    // 微信号 +++++
    private String wxId;

    // 手机号 +++++
    private String mobile;

    // 归属人
    private String toUserId;

    // 当前等级
    private Integer level;

    // 申请等级
    private Integer applyLevel;

    // 上级
    private String highUserId;

    // 推荐人 user_referee ->>referrer
    private String referrer;

    // 介绍人+++++
    private String introducer;

    // 团队名称
    private String teamName;

    // 打款金额+++++
    private Long payAmount;

    // 打款截图
    private String payPdf;

    // 省+++++
    private String province;

    // 市+++++
    private String city;

    // 区(县)+++++
    private String area;

    // 具体地址+++++
    private String address;

    // 状态
    private String status;

    // 审核人
    private String approver;

    // 审核人+++++
    private String approveName;

    // 审核时间
    private Date approveDatetime;

    // 审核备注
    private String approveNote;

    // 更新人+++++
    private String updater;

    // 更新时间+++++
    private Date updateDatetime;

    // 申请时间
    private Date applyDatetime;

    // 授权时间+++++
    private Date impowerDatetime;

    // 备注
    private String remark;

    // **************************db properties **************************
    // 代理
    private Agent agent;

    // 推荐人名称
    private String refereeName;

    // 门槛金额
    private Long impowerAmount;

    // 推荐人名字
    private String userReferrerName;

    // 介绍人姓名
    private String introduceName;

    // 关键字（用户名称、团队名称、手机号模糊查询）
    private String keyword;

    // 申请开始时间
    private Date applyDatetimeStart;

    // 申请结束时间
    private Date applyDatetimeEnd;

    // 审核开始时间
    private Date approveDatetimeStart;

    // 审核结束时间
    private Date approveDatetimeEnd;

    // 上级名称
    private String highUserName;

    // 上级名称
    private String toUserName;

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

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String userReferee) {
        this.referrer = userReferee;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPayPdf() {
        return payPdf;
    }

    public void setPayPdf(String payPdf) {
        this.payPdf = payPdf;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public String getUserReferrerName() {
        return userReferrerName;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public void setUserReferrerName(String userReferrerName) {
        this.userReferrerName = userReferrerName;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
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

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
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

    public String getApproveName() {
        return approveName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public void setImpowerAmount(Long impowerAmount) {
        this.impowerAmount = impowerAmount;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getUserRefreeName() {
        return userReferrerName;
    }

    public String getIntroduceName() {
        return introduceName;
    }

    public String getHighUserName() {
        return highUserName;
    }

    public void setReferrerName(String userRefreeName) {
        this.userReferrerName = userRefreeName;
    }

    public void setIntroduceName(String introduceName) {
        this.introduceName = introduceName;
    }

    public void setHighUserName(String highUserName) {
        this.highUserName = highUserName;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWxId() {
        return wxId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getApproveDatetimeStart() {
        return approveDatetimeStart;
    }

    public Date getApproveDatetimeEnd() {
        return approveDatetimeEnd;
    }

    public void setApproveDatetimeStart(Date approveDatetimeStart) {
        this.approveDatetimeStart = approveDatetimeStart;
    }

    public void setApproveDatetimeEnd(Date approveDatetimeEnd) {
        this.approveDatetimeEnd = approveDatetimeEnd;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeStrat(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getImpowerDatetime() {
        return impowerDatetime;
    }

    public void setImpowerDatetime(Date impowerDatetime) {
        this.impowerDatetime = impowerDatetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

}
