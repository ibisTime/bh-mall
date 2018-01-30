package com.bh.mall.ao;


import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;

public interface IAgentAO {

	String DEFAULT_ORDER_COLUMN = "code";
	/**
	 * 修改代理
	 * @param data
	 */
	public int editAgent(String code,String level,String name);
	
	/**
	 * 分页查询代理
	 * @param start
	 * @param condition
	 * @param pageSize
	 */
	public Paginable<Agent> queryAgentListPage(int start, int limit, Agent condition);
	
	/**
	 * 查询代理详情
	 * @param code
	 * @return
	 */
	public Agent getAgent(String code);

	/**
	 * 查询代理详情
	 * @param condition
	 */
	public List<Agent> queryAgentList(String level, String name);



}
