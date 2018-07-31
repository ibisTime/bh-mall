package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentReport;

//dao层 
public interface IAgentReportDAO extends IBaseDAO<AgentReport> {
    String NAMESPACE = IAgentReportDAO.class.getName().concat(".");

    /**
     * 跟新代理统计
     * @param data
     * @return 
     * @create: 2018年7月31日 下午2:23:06 LENOVO
     * @history:
     */
    int update(AgentReport data);
}
