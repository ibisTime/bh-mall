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
    public void editAgent(String code, String level, String name) {
        Agent data = agentBO.getAgent(code);
        agentBO.editAgent(data, level, name);
    }

    @Override
    public Paginable<Agent> queryAgentListPage(int start, int limit,
            Agent condition) {
        agentBO.checkByNameOrLevel(condition.getName(), condition.getLevel());
        return agentBO.getPaginable(start, limit, condition);
    }

    @Override
    public Agent getAgent(String code) {
        return agentBO.getAgent(code);
    }

    @Override
    public List<Agent> queryAgentList(Agent condition) {
        agentBO.checkByNameOrLevel(condition.getName(), condition.getLevel());
        return agentBO.queryAgentList(condition);
    }

    public Agent checkByNameOrLevel(String name, String level) {
        return agentBO.checkByNameOrLevel(name, level);
    }
}
