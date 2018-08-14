package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentReportAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.IProductReportBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.dto.req.XN627853Res;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.exception.BizException;

@Service
public class AgentReportAOImpl implements IAgentReportAO {

    @Autowired
    IAgentReportBO agentReportBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IProductReportBO productReportBO;

    @Autowired
    IAccountBO accountBO;

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

            // 可提现账户余额
            Account account = accountBO.getAccountByUser(
                agentReport.getUserId(), ECurrency.YJ_CNY.getCode());
            agentReport.setYjAmount(account.getAmount());
        }

        return page;
    }

    @Override
    public List<AgentReport> queryAgentReportList(AgentReport condition) {
        List<AgentReport> list = agentReportBO.queryAgentReportList(condition);

        Agent userName = null;
        for (AgentReport agentReport : list) {
            if (StringUtils.isNotBlank(agentReport.getUserReferee())) {
                // 推荐/介绍人转义
                userName = agentBO.getAgent(agentReport.getUserReferee());
                agentReport.setUserRefereeName(userName.getRealName());
                userName = agentBO.getAgent(agentReport.getIntroducer());
                agentReport.setIntroduceName(userName.getRealName());

            }

            // 可提现账户余额
            Account account = accountBO.getAccountByUser(
                agentReport.getUserId(), ECurrency.YJ_CNY.getCode());
            agentReport.setYjAmount(account.getAmount());
        }

        return list;
    }

    @Override
    public AgentReport getAgentReport(String code) {
        return agentReportBO.getAgentReport(code);
    }

    @Override
    public XN627853Res queryAgentReportNumber(int start, int limit,
            AgentReport condition) {
        XN627853Res res = new XN627853Res(
            agentReportBO.getTotalCount(condition));
        return res;
    }
}
