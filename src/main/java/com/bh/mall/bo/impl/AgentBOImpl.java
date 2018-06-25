package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.exception.BizException;

@Component
public class AgentBOImpl extends PaginableBOImpl<Agent> implements IAgentBO {

    @Autowired
    private IAgentDAO agentDAO;

    @Override
    public void editAgent(Agent data) {
        agentDAO.update(data);
    }

    @Override
    public Agent getAgent(String level) {
        Agent data = null;
        if (StringUtils.isNotBlank(level)) {
            Agent condition = new Agent();
            condition.setLevel(StringValidater.toInteger(level));
            data = agentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Agent> queryAgentList(Agent condition) {
        return agentDAO.selectList(condition);
    }

    @Override
    public Agent getAgentByLevel(Integer applyLevel) {
        Agent condition = new Agent();
        condition.setLevel(applyLevel);
        return agentDAO.select(condition);
    }

}
