package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentPrice;

/**
 * 产品规格定价
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:41:55 
 * @history:
 */
@Component
public interface IAgentPriceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增产品规格定价
    public String addAgentPrice(AgentPrice data);

    // 分页查询产品规格定价
    public Paginable<AgentPrice> queryAgentPricePage(int start, int limit,
            AgentPrice condition);

    // 列表查询产品规格定价
    public List<AgentPrice> queryAgentPriceList(AgentPrice condition);

    // 根据编号查询产品规格定价
    public AgentPrice getAgentPrice(String code);

}
