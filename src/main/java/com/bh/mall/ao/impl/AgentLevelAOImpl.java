package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentLevelAO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.dto.req.XN627002Req;

@Service
public class AgentLevelAOImpl implements IAgentLevelAO {

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    public void editAgentLevel(XN627002Req req) {
        AgentLevel data = agentLevelBO.getAgentLevel(req.getCode());
        data.setName(req.getName());
        data.setLevel(Integer.valueOf(req.getLevel()));
        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setRedAmount(StringValidater.toLong(req.getRedAmount()));

        data.setMinChargeAmount(
            StringValidater.toLong(req.getMinChargeAmount()));
        data.setMinSurplus(StringValidater.toLong(req.getMinSurplus()));
        data.setIsSend(req.getIsSend());
        data.setIsWare(req.getIsWare());
        data.setIsCompanyApprove(req.getIsCompanyApprove());

        data.setReNumber(Integer.valueOf(req.getReNumber()));
        data.setIsIntent(req.getIsIntent());
        data.setIsReset(req.getIsReset());
        data.setIsRealName(req.getIsRealName());
        data.setIsCompanyImpower(req.getIsCompanyImpower());

        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setIsJsAward(req.getIsJsAward());
        data.setMinCharge(Long.valueOf(req.getMinCharge()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        agentLevelBO.editAgentLevel(data);
    }

    @Override
    public Paginable<AgentLevel> queryAgentLevelListPage(int start, int limit,
            AgentLevel condition) {
        Paginable<AgentLevel> page = agentLevelBO.getPaginable(start, limit,
            condition);
        for (AgentLevel agentLevel : page.getList()) {
            // 6代表c端用户
            if (6 != agentLevel.getLevel()) {
                AgentLevel impower = agentLevelBO
                    .getAgentByLevel(agentLevel.getLevel());
                agentLevel.setIsRealName(impower.getIsRealName());
            }
        }
        return page;
    }

    @Override
    public List<AgentLevel> queryAgentLevelList(AgentLevel condition) {
        List<AgentLevel> list = agentLevelBO.queryAgentList(condition);
        for (AgentLevel agent : list) {
            // 6代表c端用户
            if (6 != agent.getLevel()) {
                AgentLevel impower = agentLevelBO
                    .getAgentByLevel(agent.getLevel());
                agent.setIsRealName(impower.getIsRealName());
            }
        }
        return list;
    }

    @Override
    public AgentLevel getAgentLevel(String code) {
        return agentLevelBO.getAgentLevel(code);
    }

    @Override
    public Object queryAgentLevelCList(AgentLevel condition) {
        List<AgentLevel> list = agentLevelBO.queryAgentList(condition);
        for (Iterator<AgentLevel> iterator = list.iterator(); iterator
            .hasNext();) {
            AgentLevel agent = iterator.next();
            if (6 == agent.getLevel()) {
                iterator.remove();
                continue;
            }
        }
        return list;
    }

}
