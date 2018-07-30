package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentReport;

//dao层 
public interface IAgentReportDAO extends IBaseDAO<AgentReport> {
    String NAMESPACE = IAgentReportDAO.class.getName().concat(".");

    int update(AgentReport data);
}
