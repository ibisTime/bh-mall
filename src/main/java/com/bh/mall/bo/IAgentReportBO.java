package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;

public interface IAgentReportBO extends IPaginableBO<AgentReport> {

    public void saveAgentReport(Agent data);

    public int removeAgentReport(String code);

    public int refreshAgentReport(AgentReport data);

    public List<AgentReport> queryAgentReportList(AgentReport condition);

    public AgentReport getAgentReport(String code);

}
