package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SjForm;

//dao层 
public interface IAgentLogDAO extends IBaseDAO<AgentLog> {

    String NAMESPACE = IAgentLogDAO.class.getName().concat(".");

    String applySqForm(SjForm sjForm);

}
