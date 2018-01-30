package com.bh.mall.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentUpgradeDAO;
import com.bh.mall.domain.AgentUpgrade;

@Component
public class AgentUpgradeBOImpl extends PaginableBOImpl<AgentUpgrade> implements IAgentUpgradeBO {

	@Autowired
	IAgentUpgradeDAO agentUpgradeDAO;
	

	@Override
	public int updateAgentUpgrade(AgentUpgrade data) {
		return agentUpgradeDAO.update(data);
	}

	@Override
	public List<AgentUpgrade> queryList(String agentCode) {
		AgentUpgrade condition = new AgentUpgrade();
		condition.setAgentCode(agentCode);
		return agentUpgradeDAO.selectList(condition);
	}

	@Override
	public AgentUpgrade getAgentUpgrade(String agentCode) {
		AgentUpgrade condition = new AgentUpgrade();
		condition.setCode(agentCode);
		return agentUpgradeDAO.select(condition);
	}

}
