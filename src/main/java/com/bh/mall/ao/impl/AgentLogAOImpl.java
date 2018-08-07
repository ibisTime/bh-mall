package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentLogAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.enums.EAgentLevel;

@Service
public class AgentLogAOImpl implements IAgentLogAO {

    @Autowired
    IAgentLogBO agentLogBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    ISYSUserBO sysUserBO;

    @Override
    public String addAgentLog(AgentLog data) {
        return null;
    }

    @Override
    public Paginable<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition) {

        Paginable<AgentLog> page = agentLogBO.getPaginable(start, limit,
            condition);

        for (AgentLog data : page.getList()) {
            // 推荐人转义
            if (StringUtils.isNotBlank(data.getReferrer())) {
                Agent userRefree = agentBO.getAgent(data.getReferrer());
                data.setReferrerName(userRefree.getRealName());
            }
            // 介绍人转义
            if (StringUtils.isNotBlank(data.getIntroducer())) {
                Agent introducer = agentBO.getAgent(data.getIntroducer());
                data.setIntroduceName(introducer.getRealName());
            }

            // 上级转义
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
                .getLevel()) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
                data.setHighUserName(sysUser.getRealName());
            } else if (StringUtils.isNotBlank(data.getHighUserId())) {
                Agent highAgent = agentBO.getAgent(data.getHighUserId());
                data.setHighUserName(highAgent.getRealName());
            }
        }
        return page;

    }

    @Override
    public List<AgentLog> queryAgentLogList(AgentLog condition) {
        List<AgentLog> list = agentLogBO.queryAgentLogList(condition);
        for (AgentLog data : list) {
            // 推荐人转义
            if (StringUtils.isNotBlank(data.getReferrer())) {
                Agent userRefree = agentBO.getAgent(data.getReferrer());
                data.setReferrerName(userRefree.getRealName());
            }
            // 介绍人转义
            if (StringUtils.isNotBlank(data.getIntroducer())) {
                Agent introducer = agentBO.getAgent(data.getIntroducer());
                data.setReferrerName(introducer.getRealName());
            }

            // 上级转义
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
                .getLevel()) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
                data.setHighUserName(sysUser.getRealName());
            } else if (StringUtils.isNotBlank(data.getHighUserId())) {
                Agent highAgent = agentBO.getAgent(data.getHighUserId());
                data.setHighUserName(highAgent.getRealName());
            }
        }
        return list;
    }

    @Override
    public AgentLog getAgentLog(String code) {
        AgentLog data = agentLogBO.getAgentLog(code);
        // 推荐人转义
        if (StringUtils.isNotBlank(data.getReferrer())) {
            Agent userRefree = agentBO.getAgent(data.getReferrer());
            data.setReferrerName(userRefree.getRealName());
        }
        // 介绍人转义
        if (StringUtils.isNotBlank(data.getIntroducer())) {
            Agent introducer = agentBO.getAgent(data.getIntroducer());
            data.setReferrerName(introducer.getRealName());
        }

        // 上级转义
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getHighUserId());
            data.setHighUserName(sysUser.getRealName());
        } else if (StringUtils.isNotBlank(data.getHighUserId())) {
            Agent highAgent = agentBO.getAgent(data.getHighUserId());
            data.setHighUserName(highAgent.getRealName());
        }
        return data;
    }

}
