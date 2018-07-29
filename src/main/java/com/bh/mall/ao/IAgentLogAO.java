package com.bh.mall.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentLog;

@Component
public interface IAgentLogAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    //
    public String addAgentLog(AgentLog data);

    public Paginable<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition);

    public List<AgentLog> queryAgentLogList(AgentLog condition);

    public AgentLog getAgentLog(String code);

}
