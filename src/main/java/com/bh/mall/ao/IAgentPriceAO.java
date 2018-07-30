package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentPrice;

@Component
public interface IAgentPriceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProductSpecsPrice(AgentPrice data);

    public Paginable<AgentPrice> queryProductSpecsPricePage(int start,
            int limit, AgentPrice condition);

    public List<AgentPrice> queryProductSpecsPriceList(
            AgentPrice condition);

    public AgentPrice getProductSpecsPrice(String code);

}
