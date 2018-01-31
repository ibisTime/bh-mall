package com.bh.mall.dto.req;

/**
 * 分页查询代理
 * @author nyc
 *
 */
public class XN627005Req {

	// 等级  （选填）
	private String level;
	// 等级  （选填）
	private String name;
	// 每页数量  （选填，默认20）
	private String limit;
	// 页码  （必填）
	private String start;
	
	// 排序字段  选填
	private String orderColumn;
	// 排序方式  选填
	private String orderDir;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	
	
}
