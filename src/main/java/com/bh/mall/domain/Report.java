package com.bh.mall.domain;

import java.util.Date;

import com.bh.mall.dao.base.ABaseDO;

/**
* 统计
* @author: nyc 
* @since: 2018-06-26 11:55:39
* @history:
*/
public class Report extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 代理Id
    private String userId;

    // 真实姓名
    private String realName;

    // 手机号
    private String mobile;

    // 微信号
    private String wxId;

    // 目前用户等级
    private Integer level;

    // 推荐人
    private String userReferee;

    // 介绍人
    private String introducer;

    // 上级用户编号
    private String highUserId;

    // 团队编号
    private String teamName;

    // 关联管理员
    private String manager;

    // 省
    private String province;

    // 市区
    private String city;

    // 区(县)
    private String area;

    // 具体地址
    private String address;

    // 授权时间
    private Date impowerDatetime;

    // 累计出货奖励
    private Long sendAward;

    // 累计介绍奖励
    private Long intrAward;

    // 累计推荐奖励
    private Long refreeAward;

    // 累积差额利润
    private Long priceSpread;

    // ******************db*******************

    // 授权开始时间
    private Date startDatetime;

    // 授权结束时间
    private Date endDatetime;

    // 推荐人名字
    private String userRefereeName;

    // 介绍人名字
    private String introduceName;

    // 管理员名字
    private String manageName;

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setHighUserId(String highUserId) {
        this.highUserId = highUserId;
    }

    public String getHighUserId() {
        return highUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Date getImpowerDatetime() {
        return impowerDatetime;
    }

    public Long getSendAward() {
        return sendAward;
    }

    public Long getIntrAward() {
        return intrAward;
    }

    public Long getRefreeAward() {
        return refreeAward;
    }

    public void setImpowerDatetime(Date impowerDatetime) {
        this.impowerDatetime = impowerDatetime;
    }

    public void setSendAward(Long sendAward) {
        this.sendAward = sendAward;
    }

    public void setIntrAward(Long intrAward) {
        this.intrAward = intrAward;
    }

    public void setRefreeAward(Long refreeAward) {
        this.refreeAward = refreeAward;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getUserRefereeName() {
        return userRefereeName;
    }

    public String getIntroduceName() {
        return introduceName;
    }

    public void setUserRefereeName(String userRefereeName) {
        this.userRefereeName = userRefereeName;
    }

    public void setIntroduceName(String introduceName) {
        this.introduceName = introduceName;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public Long getPriceSpread() {
        return priceSpread;
    }

    public void setPriceSpread(Long priceSpread) {
        this.priceSpread = priceSpread;
    }

}
