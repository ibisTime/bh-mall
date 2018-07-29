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

    private static final long serialVersionUID = 1L;

    // 微信号
    private String wxId;

    // 编码
    private String code;

    // 申请人
    private String userId;

    private String toUserId;

    private String mobile;

    // 申请等级
    private Integer applyLevel;

    // 申请时间
    private Date applyDatetime;

    // 打款金额
    private Long payAmount;

    // 打款截图
    private String payPdf;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 身份证正面照片
    private String idFront;

    // 身份证背面照片
    private String idBehind;

    // 手持身份证照片
    private String idHand;

    // 真实姓名
    private String realName;

    // 省
    private String province;

    // 市
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

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

    private Agent agent;

    private String keyWord;

    private Long impowerAmount;

    private Date applyDatetimeStart;

    private Date applyDatetimeEnd;

    private Date approveDatetimeStart;

    private Date approveDatetimeEnd;

    private Date impowerDatetimeStart;

    private Date impowerDatetimeEnd;

    private String userIdForQuery;

    /*******************************************/

    // 获取微信号
    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    // 获取编码
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    // 身份证正面照片
    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    // 身份证背面照片
    public String getIdBehind() {
        return idBehind;
    }

    public void setIdBehind(String idBehind) {
        this.idBehind = idBehind;
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
    public Agent getUser() {
        return agent;
    }

    public void setUser(Agent agent) {
        this.agent = agent;
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
