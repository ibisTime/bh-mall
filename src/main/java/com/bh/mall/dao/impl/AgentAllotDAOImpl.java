package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgentAllotDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AgentAllot;

@Repository("agentAllotDAOImpl")
public class AgentAllotDAOImpl extends AMybatisTemplate
        implements IAgentAllotDAO {

    @Override
    public int insert(AgentAllot data) {
        return super.insert(NAMESPACE.concat("insert_agentAllot"), data);
    }

    @Override
    public int delete(AgentAllot data) {
        return super.delete(NAMESPACE.concat("delete_agentAllot"), data);
    }

    @Override
    public AgentAllot select(AgentAllot condition) {
        return super.select(NAMESPACE.concat("select_agentAllot"), condition,
            AgentAllot.class);
    }

    @Override
    public long selectTotalCount(AgentAllot condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_agentAllot_count"), condition);
    }

    @Override
    public List<AgentAllot> selectList(AgentAllot condition, int start,
            int limit) {
        return super.selectList(NAMESPACE.concat("select_agentAllot"), start,
            limit, condition, AgentAllot.class);
    }

    @Override
    public List<AgentAllot> selectList(AgentAllot condition) {
        return super.selectList(NAMESPACE.concat("select_agentAllot"),
            condition, AgentAllot.class);
    }

}
