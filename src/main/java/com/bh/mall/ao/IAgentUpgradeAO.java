package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentUpgrade;

public interface IAgentUpgradeAO{

	public int updateAgentUpgrade(AgentUpgrade data);
	
	public List<AgentUpgrade> queryAgentUpgradeList(String agentCode, String code);
	
	public AgentUpgrade getAgentUpgrade(String code);

	public Paginable<AgentUpgrade> queryAgentUpgradeListPage(AgentUpgrade condition, int start, int limit);

}
