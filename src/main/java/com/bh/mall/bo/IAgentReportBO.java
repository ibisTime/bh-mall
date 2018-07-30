package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;

public interface IAgentReportBO extends IPaginableBO<AgentReport> {

    public void saveReport(Agent data);

    public int removeReport(String code);

    public int refreshReport(AgentReport data);

    public List<AgentReport> queryReportList(AgentReport condition);

    public AgentReport getReport(String code);

}
