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
        return super.insert(NAMESPACE.concat("insert_report"), data);
    }

    @Override
    public int delete(AgentReport data) {
        return super.delete(NAMESPACE.concat("delete_report"), data);
    }

    @Override
    public AgentReport select(AgentReport condition) {
        return super.select(NAMESPACE.concat("select_report"), condition,
            AgentReport.class);
    }

    @Override
    public long selectTotalCount(AgentReport condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_report_count"),
            condition);
    }

    @Override
    public List<AgentReport> selectList(AgentReport condition) {
        return super.selectList(NAMESPACE.concat("select_report"), condition,
            AgentReport.class);
    }

    @Override
    public List<AgentReport> selectList(AgentReport condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_report"), start, count,
            condition, AgentReport.class);
    }

    @Override
    public int update(AgentReport data) {
        return 0;
    }

}
