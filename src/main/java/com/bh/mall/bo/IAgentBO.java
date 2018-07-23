package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Agent;

public interface IAgentBO extends IPaginableBO<Agent> {

    public void editAgent(Agent data);

    public Agent getAgent(String code);

    public List<Agent> queryAgentList(Agent condition);

    public Agent getAgentByLevel(Integer applyLevel);

    public List<Agent> getAgentHaveWH();

}
