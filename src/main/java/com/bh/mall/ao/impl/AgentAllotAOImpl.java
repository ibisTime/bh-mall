package com.bh.mall.ao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IAgentAllotAO;
import com.bh.mall.ao.IBuserAO;
import com.bh.mall.bo.IAgentAllotBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentAllot;
import com.bh.mall.domain.BUser;
import com.bh.mall.enums.EUser;
import com.bh.mall.exception.BizException;

@Service
public class AgentAllotAOImpl implements IAgentAllotAO {

    @Autowired
    private IAgentAllotBO agentAllotBO;

    @Autowired
    private IBuserAO buserAO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    /*********************** 查询 *************************/

    @Override
    public List<AgentAllot> queryAgentAllotList(AgentAllot condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<AgentAllot> list = agentAllotBO.queryAgentAllotList(condition);
        for (AgentAllot agencyLog : list) {
            BUser userReferee = null;
            BUser buser = buserAO.doGetUser(agencyLog.getApplyUser());
            agencyLog.setUser(buser);
            /*
             * if (StringUtils.isNotBlank(agencyLog.getUserReferee())) {
             * userReferee = userAO.doGetUser(agencyLog.getUserReferee()); if
             * (userReferee != null) {
             * agencyLog.setRefereeName(userReferee.getRealName()); }
             */
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getManager())) {
                agencyLog.setManager(agencyLog.getManager());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getManager())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(agencyLog.getManager());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setManager(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return list;
    }

    /************************************************/

    @Override
    public AgentAllot getAgentAllot(String code) {
        AgentAllot data = agentAllotBO.getAgentAllot(code);
        BUser buser = buserAO.doGetUser(data.getApplyUser());
        data.setUser(buser);
        BUser userReferee = null;
        data.setUser(buser);
        /*
         * if (StringUtils.isNotBlank(data.getUserReferee())) { userReferee =
         * buserAO.doGetUser(data.getUserReferee()); if (userReferee != null) {
         * data.setRefereeName(userReferee.getRealName()); } }
         */
        // 审核人
        if (EUser.ADMIN.getCode().equals(data.getManager())) {
            data.setManager(data.getManager());
        } else {
            if (StringUtils.isNotBlank(data.getManager())) {
                BUser aprrvoeName = buserAO.doGetUser(data.getManager());
                if (null != aprrvoeName) {
                    userReferee = buserAO.getUserName(aprrvoeName.getUserId());
                    if (userReferee != null) {
                        data.setManager(userReferee.getRealName());
                    }
                }
            }

        }
        return data;
    }

    /***********************************************/

    @Override
    public Paginable<AgentAllot> queryIntentionAgentFrontPage(int start,
            int limit, AgentAllot condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        /*
         * if (EUserStatus.IGNORED.getCode().equals(condition.getStatus())) {
         * condition.setApprover(condition.getUserIdForQuery()); } else {
         * condition.setToUserId(condition.getUserIdForQuery()); //意向归属人 }
         */

        Paginable<AgentAllot> page = agentAllotBO.getPaginable(start, limit,
            condition);
        BUser userReferee = null;
        BUser buser = null;
        for (Iterator<AgentAllot> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            AgentAllot agencyLog = iterator.next();
            buser = buserAO.doGetUser(agencyLog.getApplyUser());
            if (!buser.getLastAgentLog().equals(agencyLog.getCode())) {
                iterator.remove();
                continue;
            }
            agencyLog.setUser(buser);
            if (StringUtils.isNotBlank(buser.getUserReferee())) {
                userReferee = buserAO.doGetUser(buser.getUserReferee());
                /// agencyLog.setRefereeName(userReferee.getRealName());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agencyLog.getManager())) {
                agencyLog.setManager(EUser.ADMIN.getCode());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getManager())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(agencyLog.getManager());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setManager(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return page;
    }

    /*********************** 新增日志 *************************/
    @Override
    public String addAgentAllot(AgentAllot data) {
        // insert new data
        agentAllotBO.addAgentAllot(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<AgentAllot> queryAgentAllotPage(int start, int limit,
            AgentAllot condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<AgentAllot> page = agentAllotBO.getPaginable(start, limit,
            condition);
        List<AgentAllot> list = page.getList();

        for (AgentAllot agentAllot : list) {
            BUser userReferee = null;
            BUser buser = buserAO.doGetUser(agentAllot.getApplyUser());
            agentAllot.setUser(buser);

            /*
             * if (StringUtils.isNotBlank(impowerApply.getUserReferee())) {
             * userReferee = buserAO
             * .getUserName(impowerApply.getUserReferee()); if (userReferee !=
             * null) { impowerApply.setRefereeName(userReferee.getRealName()); }
             * }
             */
            // 补全授权金额
            if (null != buser.getApplyLevel()) {
                // 代理等级表
                AgentLevel agent = agentLevelBO.getAgentByLevel(buser.getApplyLevel());
                agentAllot.setImpowerAmount(agent.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agentAllot.getManager())) {
                agentAllot.setManager(agentAllot.getManager());
            } else {
                if (StringUtils.isNotBlank(agentAllot.getManager())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(agentAllot.getManager());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agentAllot.setManager(userReferee.getRealName());
                        }
                    }
                }

            }
        }
        page.setList(list);
        return page;
    }
}
