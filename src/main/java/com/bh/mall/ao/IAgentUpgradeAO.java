package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentUpgrade;

public interface IAgentUpgradeAO{

	/**
	 * 代理升级更新
	 * @param data
	 * @return
	 */
	public int updateAgentUpgrade(AgentUpgrade data);
	
	/**
	 * 代理升级列表查询
	 * @param condition
	 * @return
	 */
	public List<AgentUpgrade> queryAgentUpgradeList(AgentUpgrade condition);
	
	/**
	 * 代理升级详情查询
	 * @param code
	 * @return
	 */
	public AgentUpgrade getAgentUpgrade(String code);

	/**
	 * 代理升级分页查询
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 */
	public Paginable<AgentUpgrade> queryAgentUpgradeListPage(AgentUpgrade condition, int start, int limit);

}
