package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Agent;

public interface IAgentDAO extends IBaseDAO<Agent>{

	String NAMESPACE = IAgentDAO.class.getName().concat(".");

	/**
	 * 更新
	 * @param data
	 * @return
	 */
	public int update(Agent data);
	
	/**
	 * 列表查询
	 */
	public List<Agent> selectList(Agent condition);
}
