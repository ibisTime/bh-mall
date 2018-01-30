package com.bh.mall.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
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
	public List<AgentUpgrade> queryList(AgentUpgrade condition) {
		return agentUpgradeDAO.selectList(condition);
	}

	@Override
	public List<AgentUpgrade> queryList(AgentUpgrade condition, int start, int limit) {
		long totalCount = agentUpgradeDAO.selectTotalCount(condition);
		Page<AgentUpgrade> page = new Page<AgentUpgrade>(start, limit, totalCount);
		return agentUpgradeDAO.selectList(condition, page.getStart(), limit);
	}

	@Override
	public AgentUpgrade getAgentUpgrade(String code) {
		AgentUpgrade condition = new AgentUpgrade();
		condition.setCode(code);
		return agentUpgradeDAO.getAgentUpgrade(condition);
	}

}
