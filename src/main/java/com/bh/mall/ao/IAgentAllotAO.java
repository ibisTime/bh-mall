package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentAllot;

public interface IAgentAllotAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增意向分配表
    //
    public String addAgentAllot(AgentAllot data);

    // 修改意向分配表
    // 同意,忽略意向 改变意向归属人
    // public String updateAgentAllot(AgentAllot data);

    //  分页查询意向代理 XN627369
    public Paginable<AgentAllot> queryAgentAllotPage(int start, int limit,
            AgentAllot condition);

    // 列表查询轨迹 XN627360
    public List<AgentAllot> queryAgentAllotList(AgentAllot condition);

    public AgentAllot getAgentAllot(String code);

    // 分页查询轨迹 XN627353
    public Paginable<AgentAllot> queryIntentionAgentFrontPage(int start,
            int limit, AgentAllot condition);

}
