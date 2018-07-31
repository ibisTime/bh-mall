package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentLog;

/**
 * 代理轨迹
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:40:10 
 * @history:
 */
@Component
public interface IAgentLogAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增代理轨迹
    public String addAgentLog(AgentLog data);

    // 分页查询代理轨迹
    public Paginable<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition);

    // 列表查询代理轨迹
    public List<AgentLog> queryAgentLogList(AgentLog condition);

    // 根据编号查询代理轨迹
    public AgentLog getAgentLog(String code);

}
