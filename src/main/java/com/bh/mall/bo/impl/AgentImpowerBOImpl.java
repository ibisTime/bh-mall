package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentDao;
import com.bh.mall.dao.IAgentImPowerDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentImpower;

@Component
public class AgentImpowerBOImpl extends PaginableBOImpl<AgentImpower> implements IAgentImpowerBO {

	@Autowired
	private IAgentImPowerDAO agentImPowerDAO;

	@Override
	public int updateAgentImpower(AgentImpower data) {
		return agentImPowerDAO.update(data);
	}

	@Override
	public AgentImpower getAgentImpower(String code) {
		AgentImpower ai = null;
		if(StringUtils.isNotBlank(code)) {
			ai = new AgentImpower();
			ai.setCode(code);
		}
		return agentImPowerDAO.getAgentImpower(ai);
	}

	@Override
	public List<AgentImpower> queryList(String code) {
		AgentImpower condition = new AgentImpower();
		condition.setAgentCode(code);
		return agentImPowerDAO.selectList(condition);
	}

	@Override
	public List<AgentImpower> queryList(AgentImpower condition, int start, int limit) {
		long totalCount = agentImPowerDAO.selectTotalCount(condition);
		Page<AgentImpower> page = new Page<AgentImpower>(start, limit, totalCount);
		return agentImPowerDAO.selectList(condition, page.getStart(), limit);
	}

	
}
