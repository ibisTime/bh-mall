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
     * @create: 2018年1月31日 下午3:38:35 nyc
     * @history:
     */
    public void editAgentImpower(XN627012Req req);

    /**
     * 代理授权分页查询
     * @param condition
     * @param pageNO
     * @param pageSize
     * @return 
     * @create: 2018年1月31日 下午3:39:13 nyc
     * @history:
     */
    public Paginable<AgentImpower> queryAgentImpowerListPage(int start,
            int limit, AgentImpower condition);

    /**
     * 代理授权列表查询
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午3:39:33 nyc
     * @history:
     */
    public List<AgentImpower> queryAgentImpowerList(AgentImpower condition);

    /**
     * 代理授权详情查询
     * @param code
     * @return 
     * @create: 2018年1月31日 下午3:39:43 nyc
     * @history:
    
     */
    public AgentImpower getAgentImpower(String code);

}
