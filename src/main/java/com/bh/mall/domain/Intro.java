package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

/**
* 售后单
* @author: chenshan 
* @since: 2018-04-03 10:06:52
* @history:
*/
public class Intro extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 等级
	private String level;

	// 奖励比例
	private String percent;

	// 申请人
	private String updater;

	// 申请时间
	private String updaterDatetime;

	// 更新人
	private String remark;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getPercent() {
		return percent;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdaterDatetime(String updaterDatetime) {
		this.updaterDatetime = updaterDatetime;
	}

	public String getUpdaterDatetime() {
		return updaterDatetime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}