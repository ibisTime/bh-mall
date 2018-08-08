package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentLevel;

/**
 * 代理等级
 * @author: LENOVO 
 * @since: 2018年8月1日 上午11:39:40 
 * @history:
 */
public interface IAgentLevelBO extends IPaginableBO<AgentLevel> {

    // 修改代理等级
    public void editAgentLevel(AgentLevel data);

    // 详情查询代理管理
    public AgentLevel getAgentLevel(String code);

    // 列表查询
    public List<AgentLevel> queryAgentList(AgentLevel condition);

    // 详情查询
    public AgentLevel getAgentByLevel(Integer level);

    public List<AgentLevel> getAgentHaveWH();

}
