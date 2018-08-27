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
import com.bh.mall.dto.req.XN627000Req;
import com.bh.mall.dto.req.XN627001Req;
import com.bh.mall.dto.req.XN627002Req;

@Service
public class AgentLevelAOImpl implements IAgentLevelAO {

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    public void editAgentLevel(XN627000Req req) {
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
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        agentLevelBO.editAgentLevel(data);
    }

    @Override
    public void editAgentLevel(XN627001Req req) {

        AgentLevel data = agentLevelBO.getAgentLevel(req.getCode());
        data.setName(req.getName());
        data.setLevel(Integer.valueOf(req.getLevel()));
        data.setIsIntent(req.getIsIntent());
        data.setIsRealName(req.getIsRealName());
        data.setIsCompanyImpower(req.getIsCompanyImpower());

        data.setIsJsAward(req.getIsJsAward());
        data.setMinCharge(StringValidater.toLong(req.getMinCharge()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        agentLevelBO.editAgentLevel(data);
    }

    @Override
    public void editAgentLevel(XN627002Req req) {
        AgentLevel data = agentLevelBO.getAgentLevel(req.getCode());
        data.setName(req.getName());
        data.setLevel(Integer.valueOf(req.getLevel()));
        data.setIsCompanyApprove(req.getIsCompanyApprove());
        data.setReNumber(StringValidater.toInteger(req.getReNumber()));
        data.setIsReset(req.getIsReset());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        agentLevelBO.editAgentLevel(data);

    }

    @Override
    public Paginable<AgentLevel> queryAgentLevelListPage(int start, int limit,
            AgentLevel condition) {
        return agentLevelBO.getPaginable(start, limit, condition);

    }

    @Override
    public List<AgentLevel> queryAgentLevelList(AgentLevel condition) {
        return agentLevelBO.queryAgentList(condition);
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
