package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentImpower;

public interface IAgentImpowerAO {
	String DEFAULT_ORDER_COLUMN = "code";
	/**
	 * 修改代理授权
	 * @param data
	 */
	public int editAgentImpower(AgentImpower data);
	
	/**
	 * 分页查询代理
	 * @param condition
	 * @param start
	 * @param pageSize
	 */
	public Paginable<AgentImpower> queryAgentImpowerListPage(AgentImpower condition, int pageNO, int pageSize);
	
	/**
	 * 查询代理详情
	 * @param string
	 */
	public List<AgentImpower> queryAgentImpowerList(String code, String agentCode);
	
	/**
	 * 查询代理详情
	 * @param code
	 * @return
	 */
	public AgentImpower getAgentImpower(String code);
}
