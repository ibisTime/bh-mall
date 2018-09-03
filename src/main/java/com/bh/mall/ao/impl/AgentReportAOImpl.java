package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bh.mall.ao.IAgentReportAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.IProductReportBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.dto.res.XN627852Res;
import com.bh.mall.dto.res.XN627853Res;
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
            }
            if (StringUtils.isNotBlank(agentReport.getIntroducer())) {
                userName = agentBO.getAgent(agentReport.getIntroducer());
                agentReport.setIntroduceName(userName.getRealName());
            }

            // 可提现账户余额
            Account account = accountBO.getAccountByUser(
                agentReport.getUserId(), ECurrency.TX_CNY.getCode());
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
            }

            if (StringUtils.isNotBlank(agentReport.getIntroducer())) {
                userName = agentBO.getAgent(agentReport.getIntroducer());
                agentReport.setIntroduceName(userName.getRealName());
            }

            // 可提现账户余额
            Account account = accountBO.getAccountByUser(
                agentReport.getUserId(), ECurrency.TX_CNY.getCode());
            agentReport.setYjAmount(account.getAmount());
        }

        return list;
    }

    @Override
    public AgentReport getAgentReport(String code) {
        AgentReport data = agentReportBO.getAgentReport(code);
        if (StringUtils.isNotBlank(data.getUserReferee())) {
            // 推荐/介绍人转义
            Agent userReferee = agentBO.getAgent(data.getUserReferee());
            data.setUserRefereeName(userReferee.getRealName());
        }
        if (StringUtils.isNotBlank(data.getIntroducer())) {
            Agent introducer = agentBO.getAgent(data.getIntroducer());
            data.setIntroduceName(introducer.getRealName());
        }
        return data;
    }

    @Override
    public XN627853Res queryAgentReportNumber(int start, int limit,
            AgentReport condition) {
        XN627853Res res = new XN627853Res(
            agentReportBO.getTotalCount(condition));
        return res;
    }

    @Override
    public XN627852Res queryAgentReportPageByP(int start, int limit,
            AgentReport condition) {
        long count = agentReportBO.getTotalCount(condition);

        Paginable<AgentReport> page = agentReportBO.getPaginable(start, limit,
            condition);
        long refreeAward = 0L;
        if (!CollectionUtils.isEmpty(page.getList())) {
            AgentReport report = page.getList().get(0);
            refreeAward = report.getRefreeAward();

        }

        Agent userName = null;
        for (AgentReport agentReport : page.getList()) {
            if (StringUtils.isNotBlank(agentReport.getUserReferee())) {
                // 推荐/介绍人转义
                userName = agentBO.getAgent(agentReport.getUserReferee());
                agentReport.setUserRefereeName(userName.getRealName());

            }
            if (StringUtils.isNotBlank(agentReport.getIntroducer())) {
                userName = agentBO.getAgent(agentReport.getIntroducer());
                agentReport.setIntroduceName(userName.getRealName());
            }

            // 可提现账户余额
            Account account = accountBO.getAccountByUser(
                agentReport.getUserId(), ECurrency.TX_CNY.getCode());
            agentReport.setYjAmount(account.getAmount());
        }

        XN627852Res res = new XN627852Res(page.getList(), count, refreeAward);
        return res;
    }
}
