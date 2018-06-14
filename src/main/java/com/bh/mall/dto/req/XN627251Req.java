package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代理申请包含推荐人
 * @author: nyc 
 * @since: 2018年3月29日 下午5:17:32 
 * @history:
 */
public class XN627251Req {

    // 用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （必填） 姓名
    @NotBlank(message = "姓名不能为空")
    private String realName;

    // （必填）微信号
    @NotBlank(message = "微信号不能为空")
    private String wxId;

    // (必填） 申请等级
    @NotBlank(message = "申请等级不能为空")
    private String applyLevel;

    // （必填）来源
    @NotBlank(message = "来源不能为空")
    private String fromInfo;

    // （必填） 电话
    @NotBlank(message = "电话不能为空")
    private String mobile;

    // （必填） 省
    @NotBlank(message = " 省不能为空")
    private String province;

    // （必填） 市
    @NotBlank(message = " 市不能为空")
    private String city;

    // （必填） 区
    @NotBlank(message = " 区不能为空")
    private String area;

    // （必填） 详细地址
    @NotBlank(message = "详细地址不能为空")
    private String address;

    // （选填）打款截图
    private String payAmount;

    // （选填）打款截图
    private String payPdf;

    // （选填）身份证反面
    private String idKind;

    // （选填）身份证反面
    private String idNo;

    // （选填）身份证反面
    private String idBehind;

    // （选填）身份证正面
    private String idFront;

    // (选填) 手持身份证
    private String idHand;

    // （选填）介绍人
    private String introducer;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayPdf() {
        return payPdf;
    }

    public void setPayPdf(String payPdf) {
        this.payPdf = payPdf;
    }

    public String getIdKind() {
        return idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
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

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(String applyLevel) {
        this.applyLevel = applyLevel;
    }

    public String getIdBehind() {
        return idBehind;
    }

    public void setIdBehind(String idBehind) {
        this.idBehind = idBehind;
    }

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public String getIdHand() {
        return idHand;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getFromInfo() {
        return fromInfo;
    }

    public void setFromInfo(String fromInfo) {
        this.fromInfo = fromInfo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

}
