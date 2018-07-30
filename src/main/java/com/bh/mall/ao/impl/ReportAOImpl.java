package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.ao.IAgentReportAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.exception.BizException;

@Service
public class ReportAOImpl implements IAgentReportAO {

    @Autowired
    IAgentReportBO reportBO;

    @Autowired
    IOrderAO orderAO;

    @Autowired
    IAgentBO agentBO;

    @Override
    public Paginable<AgentReport> queryReportPage(int start, int limit,
            AgentReport condition) {
        if (condition.getStartDatetime() != null
                && condition.getEndDatetime() != null && condition
                    .getStartDatetime().after(condition.getEndDatetime())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<AgentReport> page = reportBO.getPaginable(start, limit, condition);
        Agent userName = null;
        for (AgentReport report : page.getList()) {
            if (StringUtils.isNotBlank(report.getUserReferee())) {
                // 推荐/介绍人转义
                userName = agentBO.getAgent(report.getUserReferee());
                report.setUserRefereeName(userName.getRealName());
                userName = agentBO.getAgent(report.getIntroducer());
                report.setIntroduceName(userName.getRealName());
                // TODO 关联管理员
            }
        }

        return reportBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AgentReport> queryReportList(AgentReport condition) {
        return reportBO.queryReportList(condition);
    }

    @Override
    public AgentReport getReport(String code) {
        return reportBO.getReport(code);
    }
}
