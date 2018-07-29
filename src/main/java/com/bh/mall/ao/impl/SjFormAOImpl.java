package com.bh.mall.ao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.IWareHouseBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.SjForm;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserLevel;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SjFormAOImpl implements ISjFormAO {

    @Autowired
    private ISjFormBO uplevelApplyBO;

    @Autowired
    private IBuserAO buserAO;

    @Autowired
    private IBuserBO buserBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    IWareHouseBO wareHouseBO;

    /*************** 升级申请 **********************/
    // 升级申请
    @Override
    public void upgradeLevel(String userId, String highLevel, String payPdf,
            String payAmount) {
        BUser data = buserBO.getUser(userId);
        if (!(EUserStatus.IMPOWERED.getCode().equals(data.getStatus())
                || EUserStatus.UPGRADED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn000", "您的状态无法申请升级");
        }

        if (data.getLevel() <= StringValidater.toInteger(highLevel)) {
            throw new BizException("xn0000", "升级等级要大于当前等级");
        }
        if (StringValidater.toInteger(EUserLevel.ONE.getCode()) == data
            .getLevel()) {
            throw new BizException("xn0000", "您的等级已经为最高等级，无法继续升级");
        }

        // 查看升级所需
        AgentLevel upgrade = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(highLevel));
        AgentLevel agent = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(highLevel));

        // 推荐人数是否满足半门槛
        List<BUser> userReferee = buserBO
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
            List<WareHouse> list = wareHouseBO
                .getWareHouseByUser(data.getUserId());
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BizException("xn00000", "本等级升级云仓中不允许有余额");
            }

        }
        String status = EUserStatus.TO_COMPANYUPGRADE.getCode();
        if (StringUtils.isNotBlank(data.getHighUserId())) {
            BUser highUser = buserBO.getUser(data.getHighUserId());
            if (EUserKind.Merchant.getCode().equals(highUser.getKind())) {
                status = EUserStatus.TO_UPGRADE.getCode();
            }

        }

        data.setApplyLevel(StringValidater.toInteger(highLevel));
        data.setStatus(status);

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());
        upData.setPayAmount(StringValidater.toLong(payAmount));

        uplevelApplyBO.upgradeLevel(upData);

    }

    /*************** 通过升级申请 **********************/
    @Override
    @Transactional
    public void approveUpgrade(String userId, String approver, String remark) {
        BUser data = buserBO.getUser(userId);
        if (!(EUserStatus.TO_COMPANYUPGRADE.getCode().equals(data.getStatus())
                || EUserStatus.TO_UPGRADE.getCode().equals(data.getStatus()))) {
            throw new BizException("xn00000", "代理未申请升级");
        }
        String status = EUserStatus.IMPOWERED.getCode();
        Integer level = data.getLevel();
        if (EBoolean.YES.getCode().equals(' ')) {
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            status = EUserStatus.UPGRADED.getCode();
            AgentLevel auData = agentLevelBO
                .getAgentByLevel(data.getApplyLevel());

            // 是否推荐的代理 TODO
            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())) {
                if (!EUser.ADMIN.getCode().equals(approver)) {
                    BUser approveUser = buserBO.getUser(approver);
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
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.approveUpgrade(upData);

    }

    /*************** 取消升级申请 **********************/
    @Override
    public void cancelUplevel(String userId) {
        SjForm upData = new SjForm();
        upData.setApplyUser(userId);
        upData.setStatus(EUserStatus.TO_CANCEL.getCode());
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.addUplevelApply(upData);

    }

    /*************** 通过取消升级申请 **********************/
    public void approveUplevelCancel(String userId, String approver,
            String result, String remark) {
        BUser data = buserBO.getUser(userId);
        String status = EUserStatus.IMPOWERED.getCode();
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(EUserStatus.CANCELED.getCode());
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.approveCanenl(upData);
    }

    /*************** 通过取消升级申请 **********************/
    public void approveUplevelCanenl(String userId, String approver,
            String result, String remark) {
        BUser data = buserBO.getUser(userId);
        String status = EUserStatus.IMPOWERED.getCode();
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(EUserStatus.CANCELED.getCode());
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.approveCanenl(upData);
    }

    /*********************** 查询 *************************/

    @Override
    public List<SjForm> queryUplevelApplyList(SjForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<SjForm> list = uplevelApplyBO.queryUpLevelApplyList(condition);
        for (SjForm agencyLog : list) {
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
    public SjForm getUplevelApply(String code) {
        SjForm data = uplevelApplyBO.getUpLevelApply(code);
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
    public Paginable<SjForm> queryIntentionAgentFrontPage(int start, int limit,
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

        Paginable<SjForm> page = uplevelApplyBO.getPaginable(start, limit,
            condition);
        BUser userReferee = null;
        BUser buser = null;
        for (Iterator<SjForm> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            SjForm agencyLog = iterator.next();
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
    public String addUplevelApply(SjForm data) {
        // insert new data
        uplevelApplyBO.addUplevelApply(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<SjForm> queryUplevelApplyPage(int start, int limit,
            SjForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<SjForm> page = uplevelApplyBO.getPaginable(start, limit,
            condition);
        List<SjForm> list = page.getList();

        for (SjForm uplevelApply : list) {
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
                AgentLevel agent = agentLevelBO
                    .getAgentByLevel(buser.getApplyLevel());
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

    private void changeAmount(BUser data) {
        BUser highUser = buserBO.getUser(data.getUserId());
        // 推荐人的上级门槛转入新上级
        List<BUser> list = buserBO.getUsersByUserReferee(data.getUserId());
        BUser oldHighUser = null;
        for (BUser user : list) {
            // 被推荐代理门槛款
            Account refreeAccount = accountBO.getAccountByUser(user.getUserId(),
                ECurrency.MK_CNY.getCode());
            Account account = accountBO.getAccountByUser(highUser.getUserId(),
                ECurrency.MK_CNY.getCode());
            oldHighUser = buserBO.getCheckUser(user.getUserId());

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

        List<BUser> list = buserBO.getUsersByUserReferee(userId);
        for (BUser user : list) {
            Date date = new Date();
            user.setHighUserId(userId);
            user.setUpdater(approver);
            user.setUpdateDatetime(date);

            user.setRemark(remark);
            // String logCode = sjFormBO.refreshHighUser(user);
            // user.setLastAgentLog(logCode);
            buserBO.refreshHighUser(user);

            List<BUser> list2 = buserBO.getUsersByUserReferee(user.getUserId());
            for (BUser user2 : list2) {
                changeHighUser(highUserId, user2.getUserId(), approver, remark);
            }

        }

    }

}
