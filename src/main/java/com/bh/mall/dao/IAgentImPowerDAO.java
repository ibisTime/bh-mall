package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentImpower;

public interface IAgentImPowerDAO extends IBaseDAO<AgentImpower>{

	String NAMESPACE = IAgentImPowerDAO.class.getName().concat(".");
	/**
	 * 更新
	 * @param data
	 * @return
	 */
	public int update(AgentImpower data);
	
	
	/**
	 * 列表查询
	 */
	public List<AgentImpower> selectList(AgentImpower condition);
	
	
}
