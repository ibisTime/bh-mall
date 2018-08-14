package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isNotBlank(req.getAmount())) {
            data.setAmount(StringValidater.toLong(req.getAmount()));
        }
        if (StringUtils.isNotBlank(req.getRedAmount())) {
            data.setRedAmount(StringValidater.toLong(req.getRedAmount()));
        }
        if (StringUtils.isNotBlank(req.getMinChargeAmount())) {
            data.setMinChargeAmount(
                StringValidater.toLong(req.getMinChargeAmount()));
        }

        if (StringUtils.isNotBlank(req.getMinSurplus())) {
            data.setMinSurplus(StringValidater.toLong(req.getMinSurplus()));
        }
        data.setIsSend(req.getIsSend());
        data.setIsWare(req.getIsWare());
        data.setIsCompanyApprove(req.getIsCompanyApprove());

        if (StringUtils.isNotBlank(req.getReNumber())) {
            data.setReNumber(StringValidater.toInteger(req.getReNumber()));
        }
        data.setIsIntent(req.getIsIntent());
        data.setIsReset(req.getIsReset());
        data.setIsRealName(req.getIsRealName());
        data.setIsCompanyImpower(req.getIsCompanyImpower());

        data.setIsJsAward(req.getIsJsAward());
        if (StringUtils.isNotBlank(req.getMinCharge())) {
            data.setMinCharge(StringValidater.toLong(req.getMinCharge()));
        }

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
        for (Iterator<AgentLevel> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            AgentLevel agent = iterator.next();
            if (6 == agent.getLevel()) {
                iterator.remove();
                continue;
            }
        }
        return page;
    }

    @Override
    public List<AgentLevel> queryAgentLevelList(AgentLevel condition) {
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
