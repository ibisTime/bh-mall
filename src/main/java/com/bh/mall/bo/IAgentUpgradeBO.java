package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.AgentUpgrade;

public interface IAgentUpgradeBO extends IPaginableBO<AgentUpgrade> {

    public void editAgentUpgrade(AgentUpgrade data);

    public List<AgentUpgrade> queryAgentUpgradeList(AgentUpgrade condition);

    public AgentUpgrade getAgentUpgrade(String code);

    public AgentUpgrade getAgentUpgradeByLevel(Integer integer);
}
