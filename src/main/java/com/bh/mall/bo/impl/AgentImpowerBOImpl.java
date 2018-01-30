package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentImPowerDAO;
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
		AgentImpower condition = new AgentImpower();
		condition.setCode(code);
		return agentImPowerDAO.select(condition);
	}

	@Override
	public List<AgentImpower> queryList(String agentCode) {
		AgentImpower condition = new AgentImpower();
		condition.setAgentCode(agentCode);
		return agentImPowerDAO.selectList(condition);
	}
	
}