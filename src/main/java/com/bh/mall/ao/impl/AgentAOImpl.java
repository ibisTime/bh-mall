package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;

@Service
public class AgentAOImpl implements IAgentAO {

    @Autowired
    private IAgentBO agentBO;

    @Override
    public void editAgent(String level, String name, String amount,
            String minChargeAmount, String redAmount, String updater,
            String remark) {
        Agent data = agentBO.getAgent(level);
        agentBO.editAgent(data, name, amount, minChargeAmount, redAmount,
            updater, remark);
    }

    @Override
    public Paginable<Agent> queryAgentListPage(int start, int limit,
            Agent condition) {
        return agentBO.getPaginable(start, limit, condition);
    }

    @Override
    public Agent getAgent(String level) {
        return agentBO.getAgent(level);
    }

    @Override
    public List<Agent> queryAgentList(Agent condition) {
        return agentBO.queryAgentList(condition);
    }

}
