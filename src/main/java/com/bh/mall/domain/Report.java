package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
* 统计
* @author: nyc 
* @since: 2018-06-26 11:55:39
* @history:
*/
public class Report extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 真实姓名
	private String realName;

	// 手机号
	private String mobile;

	// 微信号
	private String wxId;

	// 目前用户等级
	private String level;

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
	private String impowerDatetime;

	// 累计出货奖励
	private String sendAward;

	// 累计介绍奖励
	private String intrAward;

	// 累计推荐奖励
	private String refreeAward;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

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

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
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

	public void setImpowerDatetime(String impowerDatetime) {
		this.impowerDatetime = impowerDatetime;
	}

	public String getImpowerDatetime() {
		return impowerDatetime;
	}

	public void setSendAward(String sendAward) {
		this.sendAward = sendAward;
	}

	public String getSendAward() {
		return sendAward;
	}

	public void setIntrAward(String intrAward) {
		this.intrAward = intrAward;
	}

	public String getIntrAward() {
		return intrAward;
	}

	public void setRefreeAward(String refreeAward) {
		this.refreeAward = refreeAward;
	}

	public String getRefreeAward() {
		return refreeAward;
	}

}