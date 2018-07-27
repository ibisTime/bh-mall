package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.dto.req.XN627002Req;
import com.bh.mall.dto.req.XN627012Req;
import com.bh.mall.dto.req.XN627022Req;

public interface IAgentLevelAO {

    String DEFAULT_ORDER_COLUMN = "level";

    // 修改代理等级
    public void editAgentLevel(XN627002Req req);

    /**
     * 分页查询代理
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午2:30:59 nyc
     * @history:
     */
    public Paginable<AgentLevel> queryAgentListPage(int start, int limit,
            AgentLevel condition);
    /**
     * 列表查询代理
     * @param condition
     * @return 
     * @create: 2018年1月31日 下午2:31:23 nyc
     * @history:
     */
    public List<AgentLevel> queryAgentList(AgentLevel condition);
    /**
     * 查询代理详情
     * @param code
     * @return 
     * @create: 2018年1月31日 下午2:31:14 nyc
     * @history:
     */
    public AgentLevel getAgentLevel(String code);

  
    
    /**
     * 无C端等级
     * @param condition
     * @return
     */
    public Object queryAgentNoCList(AgentLevel condition);
    
    
}
