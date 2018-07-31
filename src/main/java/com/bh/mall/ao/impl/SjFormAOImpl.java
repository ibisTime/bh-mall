package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.Ware;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SjFormAOImpl implements ISjFormAO {

    @Autowired
    private ISjFormBO sjFormBO;

    @Autowired
    private IAgentAO agentAO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    IWareBO wareBO;

    /*************** 升级申请 **********************/
    // 升级申请
    @Override
    public void applySjForm(String userId, String highLevel, String payPdf,
            String payAmount) {
        Agent data = agentBO.getAgent(userId);
        if (!(EUserStatus.IMPOWERED.getCode().equals(data.getStatus())
                || EUserStatus.UPGRADED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn000", "您的状态无法申请升级");
        }

        if (data.getLevel() <= StringValidater.toInteger(highLevel)) {
            throw new BizException("xn0000", "升级等级要大于当前等级");
        }
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            throw new BizException("xn0000", "您的等级已经为最高等级，无法继续升级");
        }

        // 查看升级所需
        AgentLevel upgrade = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(highLevel));
        AgentLevel agent = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(highLevel));

        // 推荐人数是否满足半门槛
        List<Agent> userReferee = agentBO
            .getUsersByUserReferee(data.getUserId());
        if (upgrade.getReNumber() >= userReferee.size()) {
            if (StringValidater.toLong(payAmount) <= agent
                .getMinChargeAmount()) {
                throw new BizException("xn00000", "您的直推人数不满足半门槛人数，打款金额不能低于"
                        + StringValidater.toLong(payAmount) / 1000);
            }
        }

        AgentLevel auData = agentLevelBO.getAgentByLevel(data.getLevel());
        // 余额是否清零
        if (EBoolean.YES.getCode().equals(auData.getIsReset())) {
            // 云仓是否有余额
            List<Ware> list = wareBO.getWareByUser(data.getUserId());
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BizException("xn00000", "本等级升级云仓中不允许有余额");
            }

        }
        String status = EUserStatus.TO_COMPANYUPGRADE.getCode();
        if (StringUtils.isNotBlank(data.getHighUserId())) {
            Agent highUser = agentBO.getAgent(data.getHighUserId());
            if (EUserKind.Merchant.getCode().equals(highUser.getKind())) {
                status = EUserStatus.TO_UPGRADE.getCode();
            }

        }

        data.setApplyLevel(StringValidater.toInteger(highLevel));
        data.setStatus(status);

        // 新增升级申请记录

        SjForm upData = new SjForm();
        upData.setUserId(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());
        upData.setPayAmount(StringValidater.toLong(payAmount));

        sjFormBO.applySjForm(upData);

    }

    /*************** 通过升级申请 **********************/
    @Override
    @Transactional
    public void approveSjForm(String userId, String approver, String result,
            String remark) {
        Agent data = agentBO.getAgent(userId);
        if (!(EUserStatus.TO_COMPANYUPGRADE.getCode().equals(data.getStatus())
                || EUserStatus.TO_UPGRADE.getCode().equals(data.getStatus()))) {
            throw new BizException("xn00000", "代理未申请升级");
        }
        String status = EUserStatus.IMPOWERED.getCode();
        Integer level = data.getLevel();
        if (EBoolean.YES.getCode().equals(result)) {
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            status = EUserStatus.UPGRADED.getCode();
            AgentLevel auData = agentLevelBO
                .getAgentByLevel(data.getApplyLevel());

            // 是否推荐的代理 TODO
            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())) {
                if (!EUser.ADMIN.getCode().equals(approver)) {
                    Agent approveUser = agentBO.getAgent(approver);
                    if (!EUserKind.Plat.getCode()
                        .equals(approveUser.getKind())) {
                        status = EUserStatus.TO_COMPANYUPGRADE.getCode();
                    }

                } else {
                    level = data.getApplyLevel();
                    // 增加账户余额
                    accountBO.changeAmount(account.getAccountNumber(),
                        EChannelType.NBZ, null, null, data.getUserId(),
                        EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                        data.getPayAmount());

                    // 推荐人的上级变成自己
                    changeHighUser(data.getUserId(), data.getUserId(), approver,
                        remark);
                    // 推荐人的上级的门槛转给自己
                    changeAmount(data);
                }
            } else {
                level = data.getApplyLevel();
                // 增加账户余额
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                    data.getPayAmount());

                // 推荐人的上级变成自己
                changeHighUser(data.getUserId(), data.getUserId(), approver,
                    remark);
                // 推荐人的上级的门槛转给自己
                changeAmount(data);

            }
        }

        data.setLevel(level);
        data.setStatus(status);
        data.setPayAmount(0L);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setUserId(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());

        sjFormBO.approveSjForm(upData);

    }

    /*************** 取消升级申请 **********************/
    @Override
    public void cancelSjForm(String userId) {
        SjForm upData = new SjForm();
        upData.setUserId(userId);
        upData.setStatus(EUserStatus.TO_CANCEL.getCode());
        upData.setApplyDatetime(new Date());

        sjFormBO.addSjForm(upData);

    }

    /*************** 通过取消升级申请 **********************/
    public void approveCancelSjForm(String userId, String approver,
            String result, String remark) {
        Agent data = agentBO.getAgent(userId);
        String status = EUserStatus.IMPOWERED.getCode();
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setUserId(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(EUserStatus.CANCELED.getCode());
        upData.setApplyDatetime(new Date());

        sjFormBO.approveCanenlSjForm(upData);
    }

    /*************** 通过取消升级申请 **********************/
    public void approveUplevelCanenl(String userId, String approver,
            String result, String remark) {
        Agent data = agentBO.getAgent(userId);
        String status = EUserStatus.IMPOWERED.getCode();
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setUserId(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(EUserStatus.CANCELED.getCode());
        upData.setApplyDatetime(new Date());

        sjFormBO.approveCanenlSjForm(upData);
    }

    /*********************** 查询 *************************/

    @Override
    public List<SjForm> querySjFormList(SjForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<SjForm> list = sjFormBO.querySjFormList(condition);
        for (SjForm sjFrom : list) {
            Agent userReferee = null;
            Agent agent = agentAO.getAgent(sjFrom.getUserId());
            sjFrom.setUser(agent);
            // 审核人 TODO
            if (EUser.ADMIN.getCode().equals(sjFrom.getApprover())) {
                sjFrom.setApprover(sjFrom.getApprover());
            } else {
                if (StringUtils.isNotBlank(sjFrom.getApprover())) {
                    Agent aprrvoeName = agentAO.getAgent(sjFrom.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = agentAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            sjFrom.setApprover(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return list;
    }

    /************************************************/

    @Override
    public SjForm getSjForm(String code) {
        SjForm data = sjFormBO.getSjForm(code);
        Agent agent = agentAO.getAgent(data.getUserId());
        data.setUser(agent);
        Agent userReferee = null;
        data.setUser(agent);
        /*
         * if (StringUtils.isNotBlank(data.getUserReferee())) { userReferee =
         * agentAO.getAgent(data.getUserReferee()); if (userReferee != null) {
         * data.setRefereeName(userReferee.getRealName()); } }
         */
        // 审核人 TODO
        if (EUser.ADMIN.getCode().equals(data.getApprover())) {
            data.setApprover(data.getApprover());
        } else {
            if (StringUtils.isNotBlank(data.getApprover())) {
                Agent aprrvoeName = agentAO.getAgent(data.getApprover());
                if (null != aprrvoeName) {
                    userReferee = agentAO.getUserName(aprrvoeName.getUserId());
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
    public Paginable<SjForm> queryISjFormFrontPage(int start, int limit,
            SjForm condition) {

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

        Paginable<SjForm> page = sjFormBO.getPaginable(start, limit, condition);
        Agent userReferee = null;
        Agent agent = null;
        for (Iterator<SjForm> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            SjForm agentLog = iterator.next();
            agent = agentAO.getAgent(agentLog.getUserId());
            if (!agent.getLastAgentLog().equals(agentLog.getCode())) {
                iterator.remove();
                continue;
            }
            agentLog.setUser(agent);
            if (StringUtils.isNotBlank(agent.getUserReferee())) {
                userReferee = agentAO.getAgent(agent.getUserReferee());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(agentLog.getApprover())) {
                agentLog.setApprover(EUser.ADMIN.getCode());
            } else {
                if (StringUtils.isNotBlank(agentLog.getApprover())) {
                    Agent aprrvoeName = agentAO
                        .getAgent(agentLog.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = agentAO
                            .getUserName(aprrvoeName.getUserId());
                        if (userReferee != null) {
                            agentLog.setApprover(userReferee.getRealName());
                        }
                    }
                }
            }
        }
        return page;
    }

    /*********************** 新增日志 *************************/
    @Override
    public String addSjForm(SjForm data) {
        // insert new data
        sjFormBO.addSjForm(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<SjForm> querySjFormPage(int start, int limit,
            SjForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<SjForm> page = sjFormBO.getPaginable(start, limit, condition);
        List<SjForm> list = page.getList();

        for (SjForm uplevelApply : list) {
            Agent userReferee = null;
            Agent agent = agentAO.getAgent(uplevelApply.getUserId());
            uplevelApply.setUser(agent);

            /*
             * if (StringUtils.isNotBlank(impowerApply.getUserReferee())) {
             * userReferee = agentAO
             * .getUserName(impowerApply.getUserReferee()); if (userReferee !=
             * null) { impowerApply.setRefereeName(userReferee.getRealName()); }
             * }
             */
            // 补全授权金额
            if (null != agent.getApplyLevel()) {
                // 代理等级表
                AgentLevel agentLevle = agentLevelBO
                    .getAgentByLevel(agent.getApplyLevel());
                uplevelApply.setRequireAmount(agentLevle.getAmount());
            }
            // 审核人
            if (EUser.ADMIN.getCode().equals(uplevelApply.getApprover())) {
                uplevelApply.setApprover(uplevelApply.getApprover());
            } else {
                if (StringUtils.isNotBlank(uplevelApply.getApprover())) {
                    Agent aprrvoeName = agentAO
                        .getAgent(uplevelApply.getApprover());
                    if (null != aprrvoeName) {
                        userReferee = agentAO
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

    private void changeAmount(Agent data) {
        Agent highUser = agentBO.getAgent(data.getUserId());
        // 推荐人的上级门槛转入新上级
        List<Agent> list = agentBO.getUsersByUserReferee(data.getUserId());
        Agent oldHighUser = null;
        for (Agent user : list) {
            // 被推荐代理门槛款
            Account refreeAccount = accountBO.getAccountByUser(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            Account account = accountBO.getAccountByUser(highUser.getUserId(),
                ECurrency.MK_CNY.getCode());
            oldHighUser = agentBO.getCheckUser(user.getUserId());

            // 旧代理上级是代理
            if (null == oldHighUser
                    || EUserKind.Plat.getCode().equals(oldHighUser.getKind())) {
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    refreeAccount.getAmount());
            }
            if (EUserKind.Merchant.getCode().equals(oldHighUser.getKind())) {
                String oldHighUserId = oldHighUser.getUserId();
                accountBO.transAmountCZB(oldHighUserId,
                    ECurrency.MK_CNY.getCode(), highUser.getUserId(),
                    ECurrency.MK_CNY.getCode(), refreeAccount.getAmount(),
                    EBizType.AJ_XGSH, EBizType.AJ_XGSH.getValue(),
                    EBizType.AJ_XGSH.getValue(), data.getUserId());
            }

            changeAmount(user);

        }
    }

    private void changeHighUser(String highUserId, String userId,
            String approver, String remark) {

        List<Agent> list = agentBO.getUsersByUserReferee(userId);
        for (Agent user : list) {
            Date date = new Date();
            user.setHighUserId(userId);
            user.setUpdater(approver);
            user.setUpdateDatetime(date);

            user.setRemark(remark);
            // String logCode = sjFormBO.refreshHighUser(user);
            // user.setLastAgentLog(logCode);
            agentBO.refreshHighUser(user);

            List<Agent> list2 = agentBO.getUsersByUserReferee(user.getUserId());
            for (Agent user2 : list2) {
                changeHighUser(highUserId, user2.getUserId(), approver, remark);
            }

        }

    }

}
