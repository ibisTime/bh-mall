package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.dto.req.XN627002Req;

@Service
public class AgentAOImpl implements IAgentAO {

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Override
    public void editAgent(XN627002Req req) {
        Agent data = agentBO.getAgent(req.getLevel());
        data.setName(req.getName());
        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setMinChargeAmount(
            StringValidater.toLong(req.getMinChargeAmount()));
        data.setMinSurplus(StringValidater.toLong(req.getMinSurplus()));

        data.setIsWareHouse(req.getIsWareHouse());
        data.setIsSend(req.getIsSend());
        data.setRedAmount(StringValidater.toLong(req.getRedAmount()));
        data.setUpdater(req.getUpdater());
        Date date = new Date();

        data.setUpdateDatetime(date);
        data.setRemark(req.getRemark());
        agentBO.editAgent(data);
    }

    @Override
    public Paginable<Agent> queryAgentListPage(int start, int limit,
            Agent condition) {
        Paginable<Agent> page = agentBO.getPaginable(start, limit, condition);
        for (Agent agent : page.getList()) {
            if (6 != agent.getLevel()) {
                AgentImpower impower = agentImpowerBO
                    .getAgentImpowerByLevel(agent.getLevel());
                agent.setIsRealName(impower.getIsRealName());
            }
        }
        return page;
    }

    @Override
    public Agent getAgent(String level) {
        return agentBO.getAgent(level);
    }

    @Override
    public List<Agent> queryAgentList(Agent condition) {
        List<Agent> list = agentBO.queryAgentList(condition);
        for (Agent agent : list) {
            if (6 != agent.getLevel()) {
                AgentImpower impower = agentImpowerBO
                    .getAgentImpowerByLevel(agent.getLevel());
                agent.setIsRealName(impower.getIsRealName());
            }
        }
        return list;
    }

    @Override
    public Object queryAgentNoCList(Agent condition) {
        List<Agent> list = agentBO.queryAgentList(condition);
        for (Iterator<Agent> iterator = list.iterator(); iterator.hasNext();) {
            Agent agent = iterator.next();
            if (6 == agent.getLevel()) {
                iterator.remove();
                continue;
            }
        }
        return list;
    }

}
