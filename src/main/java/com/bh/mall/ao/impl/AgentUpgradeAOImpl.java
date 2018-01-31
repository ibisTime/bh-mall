package com.bh.mall.ao.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.dto.req.XN627022Req;

@Service
public class AgentUpgradeAOImpl implements IAgentUpgradeAO {

    @Autowired
    IAgentUpgradeBO agentUpgradeBO;

    @Override
    public int updateAgentUpgrade(XN627022Req req) {
        AgentUpgrade data = agentUpgradeBO.getAgentUpgrade(req.getCode());
        data.setAgentCode(req.getAgentCode());
        data.setIsCompanyApprove(req.getIsCompanyApprove());
        data.setIsReset(req.getIsReset());
        data.setRecommendNumber(Integer.valueOf(req.getRecommendNumber()));
        data.setUpgradeFirstAmount(BigInteger.valueOf(Long.valueOf(req
            .getUpgradeFirstAmount())));
        return agentUpgradeBO.updateAgentUpgrade(data);
    }

    @Override
    public List<AgentUpgrade> queryAgentUpgradeList(AgentUpgrade condition) {
        return agentUpgradeBO.queryAgentUpgradeList(condition);
    }

    @Override
    public Paginable<AgentUpgrade> queryAgentUpgradeListPage(
            AgentUpgrade condition, int start, int limit) {
        return agentUpgradeBO.getPaginable(start, limit, condition);
    }

    @Override
    public AgentUpgrade getAgentUpgrade(String code) {
        return agentUpgradeBO.getAgentUpgrade(code);
    }

}
