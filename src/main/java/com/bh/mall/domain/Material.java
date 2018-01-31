package com.bh.mall.domain;

import com.bh.mall.dao.base.ABaseDO;

public class Material extends ABaseDO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8463329339507057225L;
	
	private String code;
	
	//类型
	private String type;
	// 标题
	private String title;
	// 图片
	private String pic;
	// 排序
	private String orderNo;
	// 状态   
	private String status;
	// 查看等级
	private String levelList;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLevelList() {
		return levelList;
	}
	public void setLevelList(String levelList) {
		this.levelList = levelList;
	}
	

}
