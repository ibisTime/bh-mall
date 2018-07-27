package com.bh.mall.ao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.ao.IImpowerApplyAO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IImpowerApplyBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.ImpowerApply;
import com.bh.mall.enums.EUser;
import com.bh.mall.exception.BizException;

@Service
public class ImpowerApplyAOImpl implements IImpowerApplyAO {

    @Autowired
    private IImpowerApplyBO impowerApplyBO;

    @Autowired
    private IBuserAO buserAO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    /*********************** 查询 *************************/

    @Override
    public List<ImpowerApply> queryImpowerApplyList(ImpowerApply condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<ImpowerApply> list = impowerApplyBO.queryAgencyLogList(condition);
        for (ImpowerApply agencyLog : list) {
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
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprover(agencyLog.getApprover());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprover(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return list;
    }

    /************************************************/

    @Override
    public ImpowerApply getImpowerApply(String code) {
        ImpowerApply data = impowerApplyBO.getImpowerApply(code);
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
        if (EUser.ADMIN.getCode().equals(data.getApprover())) {
            data.setApprover(data.getApprover());
        } else {
            if (StringUtils.isNotBlank(data.getApprover())) {
                BUser aprrvoeName = buserAO.doGetUser(data.getApprover());
                if (null != aprrvoeName) {
                    userReferee = buserAO.getUserName(aprrvoeName.getUserId());
                    if (userReferee != null) {
                        data.setApprover(userReferee.getRealName());
                    }
                }
            }

        }
        return data;
    }

    /***********************************************/

    @Override
    public Paginable<ImpowerApply> queryIntentionAgentFrontPage(int start,
            int limit, ImpowerApply condition) {

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

        Paginable<ImpowerApply> page = impowerApplyBO.getPaginable(start, limit,
            condition);
        BUser userReferee = null;
        BUser buser = null;
        for (Iterator<ImpowerApply> iterator = page.getList()
            .iterator(); iterator.hasNext();) {
            ImpowerApply agencyLog = iterator.next();
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
            if (EUser.ADMIN.getCode().equals(agencyLog.getApprover())) {
                agencyLog.setApprover(EUser.ADMIN.getCode());
            } else {
                if (StringUtils.isNotBlank(agencyLog.getApprover())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(agencyLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agencyLog.setApprover(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return page;
    }

    /*********************** 新增日志 *************************/
    @Override
    public String addImpowerApply(ImpowerApply data) {
        // insert new data
        impowerApplyBO.addImpowerApply(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<ImpowerApply> queryImpowerApplyPage(int start, int limit,
            ImpowerApply condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<ImpowerApply> page = impowerApplyBO.getPaginable(start, limit,
            condition);
        List<ImpowerApply> list = page.getList();

        for (ImpowerApply impowerApply : list) {
            BUser userReferee = null;
            BUser buser = buserAO.doGetUser(impowerApply.getApplyUser());
            impowerApply.setUser(buser);

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
                impowerApply.setImpowerAmount(agent.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(impowerApply.getApprover())) {
                impowerApply.setApprover(impowerApply.getApprover());
            } else {
                if (StringUtils.isNotBlank(impowerApply.getApprover())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(impowerApply.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            impowerApply.setApprover(userReferee.getRealName());
                        }
                    }
                }

            }
        }
        page.setList(list);
        return page;
    }

}
