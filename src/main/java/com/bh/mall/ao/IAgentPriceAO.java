package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentPrice;

@Component
public interface IAgentPriceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addAgentPrice(AgentPrice data);

    public Paginable<AgentPrice> queryAgentPricePage(int start,
            int limit, AgentPrice condition);

    public List<AgentPrice> queryAgentPriceList(
            AgentPrice condition);

    public AgentPrice getAgentPrice(String code);

}
