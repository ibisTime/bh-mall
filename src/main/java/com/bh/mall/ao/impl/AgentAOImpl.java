package com.bh.mall.ao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.exception.BizException;

@Service
public class AgentAOImpl implements IAgentAO {

	@Autowired
	private IAgentBO agentBO;
	
	@Override
	public int editAgent(String code,String level,String name) {
		Agent data=agentBO.getAgent(code);
		if(data==null){
			throw new BizException("xn0000", "代理不存在");
		}
		return agentBO.updateAgent(data, level, name);
	}

	@Override
	public Paginable<Agent> queryAgentListPage(int start, int limit, Agent condition) {
		return agentBO.getPaginable(start, limit, condition);
	}


	@Override
	public Agent getAgent(String code) {
		Agent data = agentBO.getAgent(code);
		if(data==null){
			throw new BizException("xn0000", "代理不存在");
		}
		return agentBO.getAgent(code);
	}

	@Override
	public List<Agent> queryAgentList(String level, String name) {
		return agentBO.queryAgentList(level, name);
	}
}
