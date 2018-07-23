package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentAllot;

public interface IAgentAllotDAO extends IBaseDAO<AgentAllot> {

    String NAMESPACE = IAgentAllotDAO.class.getName().concat(".");

}
