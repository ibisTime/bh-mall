package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgencyLogAO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.enums.EUser;
import com.bh.mall.exception.BizException;

@Service
public class AgencyLogAOImpl implements IAgencyLogAO {

    @Autowired
    IAgencyLogBO agencyLogBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Autowired
    IAddressBO addressBO;

    @Override
    public String addAgencyLog(AgencyLog data) {
        return null;
    }

    @Override
    public Paginable<AgencyLog> queryAgencyLogPage(int start, int limit,
            AgencyLog condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<AgencyLog> page = agencyLogBO.getPaginable(start, limit,
            condition);
        List<AgencyLog> list = page.getList();

        for (AgencyLog agencyLog : list) {
            Agent userReferee = null;
            Agent user = agentBO.getAgent(agencyLog.getApplyUser());
            agencyLog.setAgent(user);
            if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
                userReferee = agentBO.getAgentName(agencyLog.getUserReferee());
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
            }
            // 补全授权金额
            if (null != user.getApplyLevel()) {
                AgentLevel agent = agentLevelBO
                    .getAgentByLevel(user.getApplyLevel());
                agencyLog.setImpowerAmount(agent.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprovName(agencyLog.getApprover());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    Agent aprrvoeName = agentBO
                        .getAgent(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = agentBO
                            .getAgentName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprovName(userReferee.getRealName());
                        }
                    }
                }

            }
        }
        page.setList(list);
        return page;

    }

    @Override
    public List<AgencyLog> queryAgencyLogList(AgencyLog condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        List<AgencyLog> list = agencyLogBO.queryAgencyLogList(condition);
        for (AgencyLog agencyLog : list) {
            Agent userReferee = null;
            Agent user = agentBO.getAgent(agencyLog.getApplyUser());
            agencyLog.setAgent(user);
            if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
                userReferee = agentBO.getAgent(agencyLog.getUserReferee());
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
                // TODO 审核人
            }
        }
        return list;
    }

    @Override
    public AgencyLog getAgencyLog(String code) {
        AgencyLog data = agencyLogBO.getAgencyLog(code);
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
