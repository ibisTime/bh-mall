package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.Agent;

@Repository("agentDAOImpl")
public class AgentDAOImpl extends AMybatisTemplate implements IAgentDAO {

    @Override
    public Agent select(Agent condition) {
        return super.select(NAMESPACE.concat("select_agent"), condition,
            Agent.class);
    }

    @Override
    public long selectTotalCount(Agent condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_count"),
            condition);
    }

    @Override
    public void update(Agent data) {
        super.update(NAMESPACE.concat("update_agent"), data);
    }

    @Override
    public List<Agent> selectList(Agent condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_agent"), start, limit,
            condition, Agent.class);
    }

    @Override
    public List<Agent> selectList(Agent condition) {
        return super.selectList(NAMESPACE.concat("select_agent"), condition,
            Agent.class);
    }

    @Override
    public int insert(Agent data) {
        return 0;
    }

    @Override
    public int delete(Agent data) {
        return 0;
    }

}
