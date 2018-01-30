package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentDao;
import com.bh.mall.domain.Agent;

@Component
public class AgentBOImpl extends PaginableBOImpl<Agent> implements IAgentBO {

	@Autowired
	private IAgentDao agentLevelDao;
	
	@Override
	public int updateAgent(Agent data ,String level,String name) {
		data.setLevel(level);
		data.setName(name);
		return agentLevelDao.delete(data);
	}


	@Override
	public Agent getAgent(String code) {
		Agent condition = new Agent();
		condition.setCode(code);
		return agentLevelDao.select(condition);
	}

	@Override
	public List<Agent> selectList(String name, String level) {
		Agent condition = new Agent();
		return agentLevelDao.selectList(condition);
	}

}
