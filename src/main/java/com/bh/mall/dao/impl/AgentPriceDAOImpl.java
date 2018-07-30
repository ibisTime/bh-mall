package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgentPriceDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AgentPrice;

@Repository("agentPriceDAOImpl")
public class AgentPriceDAOImpl extends AMybatisTemplate
        implements IAgentPriceDAO {

    @Override
    public int insert(AgentPrice data) {
        return super.insert(NAMESPACE.concat("insert_agentPrice"), data);
    }

    @Override
    public int delete(AgentPrice data) {
        return super.delete(NAMESPACE.concat("delete_agentPrice"), data);
    }

    @Override
    public AgentPrice select(AgentPrice condition) {
        return super.select(NAMESPACE.concat("select_agentPrice"), condition,
            AgentPrice.class);
    }

    @Override
    public long selectTotalCount(AgentPrice condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_agentPrice_count"), condition);
    }

    @Override
    public List<AgentPrice> selectList(AgentPrice condition) {
        return super.selectList(NAMESPACE.concat("select_agentPrice"),
            condition, AgentPrice.class);
    }

    @Override
    public List<AgentPrice> selectList(AgentPrice condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_agentPrice"), start,
            count, condition, AgentPrice.class);
    }

    @Override
    public void update(AgentPrice data) {
        super.update(NAMESPACE.concat("update_agentPrice"), data);

    }

}
