package com.bh.mall.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 补充授权所需信息
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627362Req {

    // 用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （必填） 姓名
    @NotBlank(message = " 姓名不能为空")
    private String realName;

    // （必填）微信号
    @NotBlank(message = "微信号不能为空")
    private String wxId;

    // （必填） 电话
    @NotBlank(message = "电话不能为空")
    private String mobile;

    // （必填） 省
    @NotBlank(message = " 省不能为空")
    private String province;

    // （必填） 市
    @NotBlank(message = "市不能为空")
    private String city;

    // （必填） 区
    @NotBlank(message = "区不能为空")
    private String area;

    // （必填） 详细地址
    @NotBlank(message = "详细地址不能为空")
    private String address;

    // 申请等级（必填）
    @NotBlank(message = "申请等级不能为空")
    private String applyLevel;

    // 团队名称（必填）
    @NotBlank(message = "团队名称不能为空")
    private String teamName;

    // （选填）证件类型
    private String idKind;

    // （选填）证件号码
    private String idNo;

    // （选填）手持身份证照片
    private String idHand;

    // 介绍人（选填）
    private String introducer;

    public String getUserId() {
        return userId;
    }

    public String getIdHand() {
        return idHand;
    }

    public String getIdKind() {
        return idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIdHand(String idHand) {
        this.idHand = idHand;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
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

    public String getWxId() {
        return wxId;
    }

    public String getMobile() {
        return mobile;
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

    public String getApplyLevel() {
        return applyLevel;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public void setApplyLevel(String applyLevel) {
        this.applyLevel = applyLevel;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
