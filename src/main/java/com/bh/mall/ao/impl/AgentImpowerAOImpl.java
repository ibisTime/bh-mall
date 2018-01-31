package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.exception.BizException;

@Service
public class AgentImpowerAOImpl implements IAgentImpowerAO {

	@Autowired
	IAgentImpowerBO agentImpowerBO;

	@Override
	@Transactional
	public int editAgentImpower(AgentImpower data) {
		return agentImpowerBO.updateAgentImpower(data);
	}


	@Override
	@Transactional
	public List<AgentImpower> queryAgentImpowerList(AgentImpower condition) {
		return agentImpowerBO.queryAgentImpowerList(condition);
	}

	@Override
	@Transactional
	public AgentImpower getAgentImpower(String code) {
		AgentImpower data = agentImpowerBO.getAgentImpower(code);
		if(data == null) {
			throw new BizException("xn0000", "该代理不存在");
		}
		return agentImpowerBO.getAgentImpower(code);
	}


	@Override
	@Transactional
	public Paginable<AgentImpower> queryAgentImpowerListPage(AgentImpower condition, int start, int limit) {
		return agentImpowerBO.getPaginable(start, limit, condition);
	}

	

}
