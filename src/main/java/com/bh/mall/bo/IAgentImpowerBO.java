package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentImpower;

public interface IAgentImpowerBO extends IPaginableBO<AgentImpower>{
	
	public int updateAgentImpower(AgentImpower data);
	
	public List<AgentImpower> queryList(String agentCode);
	
	public AgentImpower getAgentImpower(String code);
}
