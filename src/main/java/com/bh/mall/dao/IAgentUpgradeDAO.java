package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentUpgrade;

public interface IAgentUpgradeDAO extends IBaseDAO<AgentUpgrade>{

	String NAMESPACE = IAgentUpgradeDAO.class.getName().concat(".");
	/**
	 * 更新
	 * @param data
	 * @return
	 */
	public int update(AgentUpgrade data);
	
	
	/**
	 * 列表查询
	 */
	public List<AgentUpgrade> selectList(AgentUpgrade condition);
	
}
