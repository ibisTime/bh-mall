package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentPrice;

public interface IAgentPriceDAO extends IBaseDAO<AgentPrice> {

    String NAMESPACE = IAgentPriceDAO.class.getName().concat(".");

    void update(AgentPrice data);
}
