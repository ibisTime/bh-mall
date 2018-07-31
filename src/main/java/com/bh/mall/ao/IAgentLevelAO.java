package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.dto.req.XN627002Req;

/**
 * 代理等级
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:38:48 
 * @history:
 */
public interface IAgentLevelAO {

    String DEFAULT_ORDER_COLUMN = "level";

    // 修改代理等级
    public void editAgentLevel(XN627002Req req);

    // 分页查询代理
    public Paginable<AgentLevel> queryAgentLevelListPage(int start, int limit,
            AgentLevel condition);

    // 列表查询代理
    public List<AgentLevel> queryAgentLevelList(AgentLevel condition);

    // 查询代理详情
    public AgentLevel getAgentLevel(String code);

    // 无C端等级
    public Object queryAgentLevelCList(AgentLevel condition);

}
