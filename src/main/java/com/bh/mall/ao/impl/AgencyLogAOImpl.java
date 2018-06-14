package com.bh.mall.ao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgencyLogAO;
import com.bh.mall.ao.IUserAO;
import com.bh.mall.bo.IAgencyLogBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class AgencyLogAOImpl implements IAgencyLogAO {

    @Autowired
    private IAgencyLogBO agencyLogBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private IAgentBO agentBO;

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
            User userReferee = null;
            User user = userAO.doGetUser(agencyLog.getApplyUser());
            agencyLog.setUser(user);
            if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
                userReferee = userAO.getUserName(agencyLog.getUserReferee());
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
            }
            // 补全授权金额
            if (null != user.getApplyLevel()) {
                Agent agent = agentBO.getAgentByLevel(user.getApplyLevel());
                agencyLog.setImpowerAmount(agent.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprovName(agencyLog.getApprover());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    User aprrvoeName = userAO
                        .doGetUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = userAO
                            .getUserName(aprrvoeName.getUserId());
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
            User userReferee = null;
            User user = userAO.doGetUser(agencyLog.getApplyUser());
            agencyLog.setUser(user);
            if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
                userReferee = userAO.doGetUser(agencyLog.getUserReferee());
                if (userReferee != null) {
                    agencyLog.setRefereeName(userReferee.getRealName());
                }
                // 审核人
                if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                    agencyLog.setApprovName(agencyLog.getApprover());
                } else {
                    if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                        User aprrvoeName = userAO
                            .doGetUser(agencyLog.getApprover());
                        if (null != aprrvoeName) {
                            userReferee = userAO
                                .getUserName(aprrvoeName.getUserId());
                            if (userReferee != null) {
                                agencyLog
                                    .setApprovName(userReferee.getRealName());
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public AgencyLog getAgencyLog(String code) {
        AgencyLog data = agencyLogBO.getAgencyLog(code);
        User user = userAO.doGetUser(data.getApplyUser());
        data.setUser(user);
        User userReferee = null;
        data.setUser(user);
        if (StringUtils.isNotBlank(data.getUserReferee())) {
            userReferee = userAO.doGetUser(data.getUserReferee());
            if (userReferee != null) {
                data.setRefereeName(userReferee.getRealName());
            }
        }
        // 审核人
        if (EUser.ADMIN.getCode().equals(data.getApprover())) {
            data.setApprovName(data.getApprover());
        } else {
            if (StringUtils.isNotBlank(data.getApprover())) {
                User aprrvoeName = userAO.doGetUser(data.getApprover());
                if (null != aprrvoeName) {
                    userReferee = userAO.getUserName(aprrvoeName.getUserId());
                    if (userReferee != null) {
                        data.setApprovName(userReferee.getRealName());
                    }
                }
            }

        }
        return data;
    }

    @Override
    public Paginable<AgencyLog> queryIntentionAgentFrontPage(int start,
            int limit, AgencyLog condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        if (EUserStatus.IGNORED.getCode().equals(condition.getStatus())) {
            condition.setApprover(condition.getUserIdForQuery());
        } else {
            condition.setToUserId(condition.getUserIdForQuery());
        }

        Paginable<AgencyLog> page = agencyLogBO.getPaginable(start, limit,
            condition);
        User userReferee = null;
        User user = null;
        for (Iterator<AgencyLog> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            AgencyLog agencyLog = iterator.next();
            user = userAO.doGetUser(agencyLog.getApplyUser());
            if (!user.getLastAgentLog().equals(agencyLog.getCode())) {
                iterator.remove();
                continue;
            }
            agencyLog.setUser(user);
            if (StringUtils.isNotBlank(user.getUserReferee())) {
                userReferee = userAO.doGetUser(user.getUserReferee());
                agencyLog.setRefereeName(userReferee.getRealName());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprovName(EUser.ADMIN.getCode());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    User aprrvoeName = userAO
                        .doGetUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = userAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprovName(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return page;
    }

}
