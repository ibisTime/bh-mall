package com.bh.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentPriceAO;
import com.bh.mall.bo.IAgentPriceBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentPrice;

@Service
public class AgentPriceAOImpl implements IAgentPriceAO {

    @Autowired
    IAgentPriceBO agentPriceBO;

    @Override
    public String addAgentPrice(AgentPrice data) {
        return agentPriceBO.saveAgentPrice(data);
    }

    @Override
    public Paginable<AgentPrice> queryAgentPricePage(int start,
            int limit, AgentPrice condition) {
        return agentPriceBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AgentPrice> queryAgentPriceList(AgentPrice condition) {
        return agentPriceBO.queryAgentPriceList(condition);
    }

    @Override
    public AgentPrice getAgentPrice(String code) {
        return agentPriceBO.getAgentPrice(code);
    }
}
