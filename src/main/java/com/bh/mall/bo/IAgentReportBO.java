package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.domain.SqForm;

/**
 * 代理统计
 * @author: LENOVO 
 * @since: 2018年8月1日 下午2:21:04 
 * @history:
 */
public interface IAgentReportBO extends IPaginableBO<AgentReport> {

    // 新增代理统计
    public void saveAgentReport(SqForm sqForm, Agent agent, Long jsAward);

    // 删除代理统计
    public int removeAgentReport(String code);

    // 更新代理统计
    public void refreshAgentReport(AgentReport data, SqForm sqForm, Agent agent,
            Long jsAward);

    // 列表查询代理统计
    public List<AgentReport> queryAgentReportList(AgentReport condition);

    // 详情查询
    public AgentReport getAgentReport(String code);

    // 根据代理查询统计信息
    public AgentReport getAgentReportByUser(String userId);

    // 更新累积奖励
    public void refreshAward(AgentReport data);

    public void refreshSendAward(AgentReport agentReport);

    public void refreshLevel(AgentReport report);

}
