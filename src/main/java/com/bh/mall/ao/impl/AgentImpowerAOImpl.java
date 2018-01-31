package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.dto.req.XN627012Req;

@Service
public class AgentImpowerAOImpl implements IAgentImpowerAO {

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Autowired
    IAgentBO agentBO;

    @Override
    @Transactional
    public int editAgentImpower(XN627012Req req) {
        agentBO.getAgent(req.getAgentCode());
        AgentImpower data = agentImpowerBO.getAgentImpower(req.getCode());

        data.setAgentCode(req.getAgentCode());
        data.setImpowerAmount(StringValidater.toLong(req.getImpowerAmount()));
        data.setIsCompanyImpower(req.getIsCompanyImpower());
        data.setIsIntent(req.getIsIntent());
        data.setIsIntro(req.getIsIntro());

        data.setIsRealname(req.getIsRealName());
        data.setIsSummary(req.getIsSummary());
        data.setMinCharge(StringValidater.toLong(req.getMinCharge()));
        data.setRedPercent(StringValidater.toDouble(req.getRedPercent()));
        return agentImpowerBO.updateAgentImpower(data);
    }

    @Override
    @Transactional
    public List<AgentImpower> queryAgentImpowerList(AgentImpower condition) {
        return agentImpowerBO.queryAgentImpowerList(condition);
    }

    @Override
    @Transactional
    public AgentImpower getAgentImpower(String code) {
        return agentImpowerBO.getAgentImpower(code);
    }

    @Override
    @Transactional
    public Paginable<AgentImpower> queryAgentImpowerListPage(int start,
            int limit, AgentImpower condition) {
        return agentImpowerBO.getPaginable(start, limit, condition);
    }

}
