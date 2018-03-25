package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentUpgradeAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.dto.req.XN627022Req;

@Service
public class AgentUpgradeAOImpl implements IAgentUpgradeAO {

    @Autowired
    IAgentUpgradeBO agentUpgradeBO;

    @Autowired
    IAgentBO agentBO;

    @Override
    @Transactional
    public void editAgentUpgrade(XN627022Req req) {
        AgentUpgrade data = agentUpgradeBO.getAgentUpgrade(req.getCode());
        data.setLevel(Integer.valueOf(req.getLevel()));

        data.setIsCompanyApprove(req.getIsCompanyApprove());
        data.setIsReset(req.getIsReset());
        data.setReNumber(Integer.valueOf(req.getReNumber()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        agentUpgradeBO.editAgentUpgrade(data);
    }

    @Override
    @Transactional
    public List<AgentUpgrade> queryAgentUpgradeList(AgentUpgrade condition) {
        return agentUpgradeBO.queryAgentUpgradeList(condition);
    }

    @Override
    @Transactional
    public Paginable<AgentUpgrade> queryAgentUpgradeListPage(int start,
            int limit, AgentUpgrade condition) {
        return agentUpgradeBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public AgentUpgrade getAgentUpgrade(String code) {
        return agentUpgradeBO.getAgentUpgrade(code);
    }

}
