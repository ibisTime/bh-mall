package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.exception.BizException;

@Service
public class AgentUpgradeAOImpl implements IAgentUpgradeAO {

	@Autowired
	IAgentUpgradeBO agentUpgradeBO;
	

	@Override
	@Transactional
	public int updateAgentUpgrade(AgentUpgrade data) {
		return agentUpgradeBO.updateAgentUpgrade(data);
	}

	@Override
	@Transactional
	public List<AgentUpgrade> queryList(AgentUpgrade condition) {
		return agentUpgradeBO.queryList(condition);
	}

	@Override
	@Transactional
	public Paginable<AgentUpgrade> selectPageList(AgentUpgrade condition, int start, int limit) {
		return agentUpgradeBO.getPaginable(start, limit, condition);
	}

	@Override
	@Transactional
	public AgentUpgrade getAgentUpgrade(String code) {
		AgentUpgrade data = agentUpgradeBO.getAgentUpgrade(code);
		if(data == null) {
			throw new BizException("xn0000", "该代理不存在");
		}
		return agentUpgradeBO.getAgentUpgrade(code);
	}

	

}
