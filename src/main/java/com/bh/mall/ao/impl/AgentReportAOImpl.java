package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentReportAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.exception.BizException;

@Service
public class AgentReportAOImpl implements IAgentReportAO {

    @Autowired
    IAgentReportBO agentReportBO;

    @Autowired
    IAgentBO agentBO;

    @Override
    public Paginable<AgentReport> queryAgentReportPage(int start, int limit,
            AgentReport condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<AgentReport> page = agentReportBO.getPaginable(start, limit,
            condition);
        Agent userName = null;
        for (AgentReport agentReport : page.getList()) {
            if (StringUtils.isNotBlank(agentReport.getUserReferee())) {
                // 推荐/介绍人转义
                userName = agentBO.getAgent(agentReport.getUserReferee());
                agentReport.setUserRefereeName(userName.getRealName());
                userName = agentBO.getAgent(agentReport.getIntroducer());
                agentReport.setIntroduceName(userName.getRealName());

            }
        }

        return agentReportBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AgentReport> queryAgentReportList(AgentReport condition) {
        return agentReportBO.queryAgentReportList(condition);
    }

    @Override
    public AgentReport getAgentReport(String code) {
        return agentReportBO.getAgentReport(code);
    }
}
