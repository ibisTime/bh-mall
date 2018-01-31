package com.bh.mall.dto.req;

/**
 * 代理升级分页查询
 * @author nyc
 *
 */
public class XN627025Req {

	// 代理编号 （选填）
	private String agentCode;
	// 每页数量 （选填）
	private String limit;
	// 排序字段 （选填）
	private String orderColumn;
	// 排序方式 （选填）
	private String orderDir;
	// 页码 （必填）
	private String start;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	
}
