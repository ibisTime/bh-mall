package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentLevel;

public interface IAgentLevelBO extends IPaginableBO<AgentLevel> {

    public void editAgentLevel(AgentLevel data);

    public AgentLevel getAgentLevel(String code);

    public List<AgentLevel> queryAgentList(AgentLevel condition);

    public AgentLevel getAgentByLevel(Integer applyLevel);

    public List<AgentLevel> getAgentHaveWH();

}
