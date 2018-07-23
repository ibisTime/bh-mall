package com.bh.mall.ao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.ao.IUpLevelApplyAO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.IUpLevelApplyBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.UpLevelApply;
import com.bh.mall.enums.EUser;
import com.bh.mall.exception.BizException;

@Service
public class UpLevelApplyAOImpl implements IUpLevelApplyAO {

    @Autowired
    private IUpLevelApplyBO uplevelApplyBO;

    @Autowired
    private IBuserAO buserAO;

    @Autowired
    private IBuserBO buserBO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    IAgentUpgradeBO agentUpgradeBO;

    @Autowired
    IWareHouseBO wareHouseBO;

    /*********************** 查询 *************************/

    @Override
    public List<UpLevelApply> queryUplevelApplyList(UpLevelApply condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<UpLevelApply> list = uplevelApplyBO
            .queryUpLevelApplyList(condition);
        for (UpLevelApply agencyLog : list) {
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
    public UpLevelApply getUplevelApply(String code) {
        UpLevelApply data = uplevelApplyBO.getUpLevelApply(code);
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
    public Paginable<UpLevelApply> queryIntentionAgentFrontPage(int start,
            int limit, UpLevelApply condition) {

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

        Paginable<UpLevelApply> page = uplevelApplyBO.getPaginable(start, limit,
            condition);
        BUser userReferee = null;
        BUser buser = null;
        for (Iterator<UpLevelApply> iterator = page.getList()
            .iterator(); iterator.hasNext();) {
            UpLevelApply agencyLog = iterator.next();
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
    public String addUplevelApply(UpLevelApply data) {
        // insert new data
        uplevelApplyBO.addUplevelApply(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<UpLevelApply> queryUplevelApplyPage(int start, int limit,
            UpLevelApply condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<UpLevelApply> page = uplevelApplyBO.getPaginable(start, limit,
            condition);
        List<UpLevelApply> list = page.getList();

        for (UpLevelApply uplevelApply : list) {
            BUser userReferee = null;
            BUser buser = buserAO.doGetUser(uplevelApply.getApplyUser());
            uplevelApply.setUser(buser);

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
                Agent agent = agentBO.getAgentByLevel(buser.getApplyLevel());
                uplevelApply.setImpowerAmount(agent.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(uplevelApply.getApprover())) {
                uplevelApply.setApprover(uplevelApply.getApprover());
            } else {
                if (StringUtils.isNotBlank(uplevelApply.getApprover())) {
                    BUser aprrvoeName = buserAO
                        .doGetUser(uplevelApply.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = buserAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            uplevelApply.setApprover(userReferee.getRealName());
                        }
                    }
                }

            }
        }
        page.setList(list);
        return page;
    }

}
