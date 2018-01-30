package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.AgentUpgrade;

public interface IAgentUpgradeAO{

	public int updateAgentUpgrade(AgentUpgrade data);
	
	public List<AgentUpgrade> queryList(AgentUpgrade condition);
	
	public AgentUpgrade getAgentUpgrade(String code);

	public Paginable<AgentUpgrade> selectPageList(AgentUpgrade condition, int start, int limit);
}
