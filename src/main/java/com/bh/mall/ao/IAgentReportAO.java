package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentReport;

@Component
public interface IAgentReportAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<AgentReport> queryAgentReportPage(int start, int limit,
            AgentReport condition);

    public List<AgentReport> queryAgentReportList(AgentReport condition);

    public AgentReport getAgentReport(String code);

}
