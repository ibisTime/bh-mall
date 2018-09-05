package com.bh.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bh.mall.dao.IAgentReportDAO;
import com.bh.mall.dao.base.support.AMybatisTemplate;
import com.bh.mall.domain.AgentReport;

@Repository("agentReportDAOImpl")
public class AgentReportDAOImpl extends AMybatisTemplate
        implements IAgentReportDAO {

    @Override
    public int insert(AgentReport data) {
        return super.insert(NAMESPACE.concat("insert_agentReport"), data);
    }

    @Override
    public int delete(AgentReport data) {
        return super.delete(NAMESPACE.concat("delete_agentReport"), data);
    }

    @Override
    public AgentReport select(AgentReport condition) {
        return super.select(NAMESPACE.concat("select_agentReport"), condition,
            AgentReport.class);
    }

    @Override
    public long selectTotalCount(AgentReport condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_agentReport_count"), condition);
    }

    @Override
    public List<AgentReport> selectList(AgentReport condition) {
        return super.selectList(NAMESPACE.concat("select_agentReport"),
            condition, AgentReport.class);
    }

    @Override
    public List<AgentReport> selectList(AgentReport condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_agentReport"), start,
            count, condition, AgentReport.class);
    }

    @Override
    public void update(AgentReport data) {
        super.update(NAMESPACE.concat("update_agentReport"), data);
    }

    @Override
    public void updateAward(AgentReport data) {
        super.update(NAMESPACE.concat("update_award"), data);
    }

    @Override
    public void updateSendAward(AgentReport data) {
        super.update(NAMESPACE.concat("update_sendAward"), data);
    }

    @Override
    public void updateLevel(AgentReport data) {
        super.update(NAMESPACE.concat("update_level"), data);
    }

}
