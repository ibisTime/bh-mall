package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
 * 代理授权单
 * @author: clockorange 
 * @since: Jul 11, 2018 2:05:50 PM 
 * @history:
 */
public class SqForm extends ABaseDO {

    private static final long serialVersionUID = -3497398052556679737L;

    // 申请人
    private String userId;

    // 真实姓名
    private String realName;

    // 微信号
    private String wxId;

    // 电话
    private String mobile;

    // 申请等级
    private Integer applyLevel;

    // 分配上级
    private String toUserId;

    // 团队 名称
    private String teamName;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 手持身份证照片
    private String idHand;

    // 介绍人
    private String introducer;

    // 推荐人
    private String referrer;

    // 省
    private String province;

    // 市
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 状态
    private String status;

    // 申请时间
    private Date applyDatetime;

    // 审核人
    private String approver;

    // 审核人名称
    private String approveName;

    // 审核时间
    private Date approveDatetime;

    // 授权时间
    private Date impowerDatetime;

    // 备注
    private String remark;

    // *******************db*****************

    // 代理
    private Agent agent;

    // 关键词
    private String keyWord;

    // 申请开始时间
    private Date applyDatetimeStart;

    // 申请结束时间
    private Date applyDatetimeEnd;

    // 通过授权开始时间
    private Date approveDatetimeStart;

    // 通过授权结束时间
    private Date approveDatetimeEnd;

    // 用户id 查询
    private String userIdForQuery;

    // 获取微信号
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    // 获取ID
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    // 获取手机号
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // 证件类型
    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    // 证件号码
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    // 手持身份证照片
    public String getIdHand() {
        return idHand;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

    // 真实姓名
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    // 省， 市， 区 & 详细地址
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    public Agent getUser() {
        return agent;
    }

    public void setUser(Agent agent) {
        this.agent = agent;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
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

    public String getApproveName() {
        return approveName;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Date getImpowerDatetime() {
        return impowerDatetime;
    }

    public void setImpowerDatetime(Date impowerDatetime) {
        this.impowerDatetime = impowerDatetime;
    }

}
