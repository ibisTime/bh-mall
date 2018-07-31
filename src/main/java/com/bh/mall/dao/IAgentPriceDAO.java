package com.bh.mall.dao;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.AgentPrice;

public interface IAgentPriceDAO extends IBaseDAO<AgentPrice> {

    String NAMESPACE = IAgentPriceDAO.class.getName().concat(".");

    /**
     * 更新产品规格定价表
     * @param data 
     * @create: 2018年7月31日 下午2:22:25 LENOVO
     * @history:
     */
    void update(AgentPrice data);
}
