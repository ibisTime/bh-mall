package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentUpgradeDAO;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.exception.BizException;

@Component
public class AgentUpgradeBOImpl extends PaginableBOImpl<AgentUpgrade> implements
        IAgentUpgradeBO {

    @Autowired
    IAgentUpgradeDAO agentUpgradeDAO;

    @Override
    public void editAgentUpgrade(AgentUpgrade data) {
        agentUpgradeDAO.update(data);
    }

    @Override
    public List<AgentUpgrade> queryAgentUpgradeList(AgentUpgrade condition) {
        return agentUpgradeDAO.selectList(condition);
    }

    @Override
    public AgentUpgrade getAgentUpgrade(String code) {
        AgentUpgrade data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentUpgrade condition = new AgentUpgrade();
            condition.setCode(code);
            data = agentUpgradeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000", "代理升级编号不存在");
            }
        }
        return data;
    }

}
