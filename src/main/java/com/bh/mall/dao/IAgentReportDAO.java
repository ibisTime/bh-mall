package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentReport;

//dao层 
public interface IAgentReportDAO extends IBaseDAO<AgentReport> {
    String NAMESPACE = IAgentReportDAO.class.getName().concat(".");

    void update(AgentReport data);

    void updateAward(AgentReport data);

    void updateSendAward(AgentReport data);

    void updateLevel(AgentReport data);
}
