package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgentLevelDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AgentLevel;

@Repository("agentLevelDAOImpl")
public class AgentLevelDAOImpl extends AMybatisTemplate implements IAgentLevelDAO {

    @Override
    public AgentLevel select(AgentLevel condition) {
        return super.select(NAMESPACE.concat("select_agentLevel"), condition,
            AgentLevel.class);
    }

    @Override
    public long selectTotalCount(AgentLevel condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_agentLevel_count"),
            condition);
    }

    @Override
    public void update(AgentLevel data) {
        super.update(NAMESPACE.concat("update_agentLevel"), data);
    }

    @Override
    public List<AgentLevel> selectList(AgentLevel condition, int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_agentLevel"), start, limit,
            condition, AgentLevel.class);
    }

    @Override
    public List<AgentLevel> selectList(AgentLevel condition) {
        return super.selectList(NAMESPACE.concat("select_agentLevel"), condition,
            AgentLevel.class);
    }

    @Override
    public AgentLevel selectByLevel(AgentLevel condition) {
        return super.select(NAMESPACE.concat("select_agentLevel"), condition,
            AgentLevel.class);
    }

    @Override
    public int insert(AgentLevel data) {
        return 0;
    }

    @Override
    public int delete(AgentLevel data) {
        return 0;
    }

}
