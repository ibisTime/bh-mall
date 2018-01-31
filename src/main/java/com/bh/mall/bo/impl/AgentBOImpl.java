package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentDAO;
import com.bh.mall.domain.Agent;
import com.bh.mall.exception.BizException;

@Component
public class AgentBOImpl extends PaginableBOImpl<Agent> implements IAgentBO {

    @Autowired
    private IAgentDAO agentDAO;

    @Override
    public int updateAgent(Agent data, String level, String name) {
        data.setLevel(level);
        data.setName(name);
        return agentDAO.update(data);
    }

    @Override
    public Agent getAgent(String code) {
        Agent data = null;
        if (StringUtils.isNotBlank(code)) {
            Agent condition = new Agent();
            condition.setCode(code);
            data = agentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Agent> queryAgentList(String level, String name) {
        Agent condition = new Agent();
        condition.setLevel(level);
        condition.setName(name);
        return agentDAO.selectList(condition);
    }

}
