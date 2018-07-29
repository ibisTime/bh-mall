package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAgentLevelDAO;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.exception.BizException;

@Component
public class AgentLevelBOImpl extends PaginableBOImpl<AgentLevel> implements IAgentLevelBO {

    @Autowired
    private IAgentLevelDAO agentLevelDAO;

    @Override
    public void editAgent(AgentLevel data) {
        agentLevelDAO.update(data);
    }

    @Override
    public AgentLevel getAgentLevel(String code) {
        AgentLevel data = null;
        if (StringUtils.isNotBlank(code)) {
            AgentLevel condition = new AgentLevel();
            condition.setCode(code);
            data = agentLevelDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<AgentLevel> queryAgentList(AgentLevel condition) {
        return agentLevelDAO.selectList(condition);
    }

    @Override
    public AgentLevel getAgentByLevel(Integer applyLevel) {
        AgentLevel condition = new AgentLevel();
        condition.setLevel(applyLevel);
        return agentLevelDAO.select(condition);
    }

    @Override
    public List<AgentLevel> getAgentHaveWH() {
        AgentLevel condition = new AgentLevel();
        condition.setIsWare(EBoolean.YES.getCode());
        return agentLevelDAO.selectList(condition);
    }

}
