package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.dto.req.XN627012Req;

public interface IAgentImpowerAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 修改代理授权
     * @param req
     */
    public int editAgentImpower(XN627012Req req);

    /**
     * 分页查询代理授权
     * @param condition
     * @param start
     * @param limit
     */
    public Paginable<AgentImpower> queryAgentImpowerListPage(int start,
            int limit, AgentImpower condition);

    /**
     * 代理授权列表查询
     * @param code
     * @param agentCode
     * @return
     */
    public List<AgentImpower> queryAgentImpowerList(AgentImpower condition);

    /**
     * 查询代理授权详情
     * @param code
     * @return
     */
    public AgentImpower getAgentImpower(String code);
}
