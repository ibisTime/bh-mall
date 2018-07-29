package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentLog;

//dao层 
public interface IAgentLogDAO extends IBaseDAO<AgentLog> {

    String NAMESPACE = IAgentLogDAO.class.getName().concat(".");
}
