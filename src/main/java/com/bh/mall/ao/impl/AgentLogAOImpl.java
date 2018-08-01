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
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.enums.EUser;

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

    @Override
    public String addAgentLog(AgentLog data) {
        return null;
    }

    @Override
    public Paginable<AgentLog> queryAgentLogPage(int start, int limit,
            AgentLog condition) {

        Paginable<AgentLog> page = agentLogBO.getPaginable(start, limit,
            condition);
        List<AgentLog> list = page.getList();

        for (AgentLog agentLog : list) {
            Agent userReferee = null;
            Agent agent = agentBO.getAgent(agentLog.getApplyUser());
            agentLog.setAgent(agent);
            if (StringUtils.isNotBlank(agentLog.getUserReferee())) {
                userReferee = agentBO.getAgentName(agentLog.getUserReferee());
                if (userReferee != null) {
                    agentLog.setRefereeName(userReferee.getRealName());
                }
            }
            // 补全授权金额
            if (null != agent.getApplyLevel()) {
                AgentLevel agentLevel = agentLevelBO
                    .getAgentByLevel(agent.getApplyLevel());
                agentLog.setImpowerAmount(agentLevel.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agentLog.getApprover())) {
                agentLog.setApprovName(agentLog.getApprover());
            } else {
                if (StringUtils.isNotBlank(agentLog.getApprover())) {
                    Agent aprrvoeName = agentBO
                        .getAgent(agentLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = agentBO
                            .getAgentName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agentLog.setApprovName(userReferee.getRealName());
                        }
                    }
                }

            }
        }
        page.setList(list);
        return page;

    }

    @Override
    public List<AgentLog> queryAgentLogList(AgentLog condition) {
        List<AgentLog> list = agentLogBO.queryAgentLogList(condition);
        for (AgentLog agentLog : list) {
            Agent userReferee = null;
            Agent user = agentBO.getAgent(agentLog.getApplyUser());
            agentLog.setAgent(user);
            if (StringUtils.isNotBlank(agentLog.getUserReferee())) {
                userReferee = agentBO.getAgent(agentLog.getUserReferee());
                if (userReferee != null) {
                    agentLog.setRefereeName(userReferee.getRealName());
                }
                // TODO 审核人(代理/平台)
            }
        }
        return list;
    }

    @Override
    public AgentLog getAgentLog(String code) {
        AgentLog data = agentLogBO.getAgentLog(code);
        Agent user = agentBO.getAgent(data.getApplyUser());
        data.setAgent(user);
        Agent userReferee = null;
        data.setAgent(user);
        if (StringUtils.isNotBlank(data.getUserReferee())) {
            userReferee = agentBO.getAgent(data.getUserReferee());
            if (userReferee != null) {
                data.setRefereeName(userReferee.getRealName());
            }
        }
        return data;
    }

}
