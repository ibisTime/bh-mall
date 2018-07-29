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

    /**
     * 627002_修改代理管理
     */
    @Override
    public void editAgentLevel(XN627002Req req) {
        AgentLevel data = agentLevelBO.getAgentLevel(req.getCode());
        /*
         * if() { }
         */
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

        data.setImpowerAmount(Integer.valueOf(req.getImpowerAmount()));
        data.setIsJsAward(req.getIsJsAward());
        data.setMinCharge(Long.valueOf(req.getMinCharge()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        agentLevelBO.editAgent(data);
    }

    /**
     * 627005_分页查询代理管理
     */
    @Override
    public Paginable<AgentLevel> queryAgentListPage(int start, int limit,
            AgentLevel condition) {
        Paginable<AgentLevel> page = agentLevelBO.getPaginable(start, limit,
            condition);
        for (AgentLevel agent : page.getList()) {
            // 6代表c端用户
            if (6 != agent.getLevel()) {
                AgentLevel impower = agentLevelBO
                    .getAgentByLevel(agent.getLevel());
                agent.setIsRealName(impower.getIsRealName());
            }
        }
        return page;
    }

    /**
     * 627006_列表查询代理管理
     */
    @Override
    public List<AgentLevel> queryAgentList(AgentLevel condition) {
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

    /**
     * 627007_详情查询代理管理
     */
    @Override
    public AgentLevel getAgentLevel(String code) {
        return agentLevelBO.getAgentLevel(code);
    }

    /**
     * 627008_无C端等级
     */
    @Override
    public Object queryAgentNoCList(AgentLevel condition) {
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
