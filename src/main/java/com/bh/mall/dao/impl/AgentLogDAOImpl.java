package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgentLogDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.SqForm;

@Repository("agentLogDAOImpl")
public class AgentLogDAOImpl extends AMybatisTemplate implements IAgentLogDAO {

    @Override
    public int insert(AgentLog data) {
        return super.insert(NAMESPACE.concat("insert_agentLog"), data);
    }

    @Override
    public int delete(AgentLog data) {
        return super.delete(NAMESPACE.concat("delete_agentLog"), data);
    }

    @Override
    public AgentLog select(AgentLog condition) {
        return super.select(NAMESPACE.concat("select_agentLog"), condition,
            AgentLog.class);
    }

    @Override
    public long selectTotalCount(AgentLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_agentLog_count"),
            condition);
    }

    @Override
    public List<AgentLog> selectList(AgentLog condition) {
        return super.selectList(NAMESPACE.concat("select_agentLog"), condition,
            AgentLog.class);
    }

    @Override
    public List<AgentLog> selectList(AgentLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_agentLog"), start,
            count, condition, AgentLog.class);
    }

    @Override
    public String applySqForm(SqForm data) {
        return null;
    }

    @Override
    public String applySjForm(SjForm sjForm) {
        return null;
    }

}
