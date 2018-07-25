package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.IAgentAllotBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IAgentUpgradeBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.IImpowerApplyBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISmsOutBO;
import com.bh.mall.bo.IUpLevelApplyBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.MD5Util;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AgentAllot;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.AgentUpgrade;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.ImpowerApply;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.UpLevelApply;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SYSUserAOImpl implements ISYSUserAO {

    // 平台端登录接口api
    private static Logger logger = Logger.getLogger(SYSUserAOImpl.class);

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IBuserBO buserBO;

    @Autowired
    IAddressBO addressBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IOrderBO orderBO;

    @Autowired
    IInnerOrderBO innerOrderBO;

    @Autowired
    IAfterSaleBO afterSaleBO;

    @Autowired
    IAgentAllotBO agentAllotBO;

    @Autowired
    IUpLevelApplyBO uplevelApplyBO;

    @Autowired
    IImpowerApplyBO impowerApplyBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Autowired
    IAgentUpgradeBO agentUpgradeBO;

    /*************** 注册 **********************/
    @Override
    public String addUser(String mobile, String loginPwd, String realName,
            String photo) {
        sysUserBO.isMobileExist(mobile, ESystemCode.BH.getCode());
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setMobile(mobile);
        data.setRealName(realName);
        data.setLoginName(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setPhoto(photo);
        data.setSystemCode(ESystemCode.BH.getCode());
        sysUserBO.doSaveUser(data);
        return userId;
    }

    /*************** 登录 **********************/
    // 用户登录
    @Override
    public String doLogin(String loginName, String loginPwd) {
        SYSUser condition = new SYSUser();

        condition.setLoginName(loginName);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        SYSUser user = userList2.get(0);

        return user.getUserId();
    }

    // 重置登录密码
    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        SYSUser user = sysUserBO.getCheckUser(userId);
        sysUserBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    // 验证登录密码
    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        SYSUser condition = new SYSUser();
        condition.setUserId(userId);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        SYSUser user = sysUserBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }

    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String kind, String newMobile,
            String smsCaptcha) {
        SYSUser user = sysUserBO.getCheckUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        sysUserBO.isMobileExist(newMobile, user.getSystemCode());
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "627282",
            user.getSystemCode());
        sysUserBO.resetBindMobile(user, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072", user.getCompanyCode(), user.getSystemCode());

    }

    /*************** 查询 **********************/
    // 查询所有意向代理申请
    @Override
    public Paginable<BUser> queryIntentionAgentPage(int start, int limit,
            BUser condition) {
        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<BUser> page = buserBO.getPaginable(start, limit, condition);
        BUser highUser = null;
        for (BUser data : page.getList()) {
            if (StringUtils.isNotBlank(data.getHighUserId())) {
                highUser = buserBO.getUser(data.getHighUserId());
                data.setHighUser(highUser);
            }
        }

        return page;
    }

    // 查询所有代理
    @Override
    public List<BUser> queryAgentPage(BUser condition) {
        if (StringUtils.isBlank(condition.getHighUserId())) {
            condition.setLevel(1);
        }
        List<BUser> list = buserBO.queryUserList(condition);
        if (StringUtils.isNotBlank(condition.getHighUserId())) {
            list = getAllLowUser(list);
        }
        return list;
    }

    // 下级
    public List<BUser> getAllLowUser(List<BUser> list) {
        for (int i = 0; i < list.size(); i++) {
            BUser buser = list.get(i);
            BUser condition = new BUser();
            condition.setHighUserId(buser.getUserId());
            List<BUser> userList = buserBO.queryUserList(condition);
            if (CollectionUtils.isNotEmpty(userList)) {
                buser.setUserList(userList);
                getAllLowUser(userList);
            }
        }
        return list;
    }

    /*************** 意向分配 **********************/

    @Override
    public void allotAgency(String userId, String toUserId, String manager,
            String approver) {
        BUser data = buserBO.getUser(userId);
        String status = EUserStatus.TO_APPROVE.getCode();
        if (!EUserKind.Merchant.getCode().equals(data.getKind())) {
            throw new BizException("xn0000", "该用户不是代理，无法分配");
        }
        if (StringUtils.isNotBlank(toUserId)) {
            BUser toUser = buserBO.getUser(toUserId);
            if (data.getApplyLevel() <= toUser.getLevel()) {
                throw new BizException("xn0000", "意向代理的等级不能大于归属人的等级");
            }
            status = EUserStatus.ALLOTED.getCode(); // 已分配
        } else {
            status = EUserStatus.ADD_INFO.getCode();
        }

        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setManager(manager);
        data.setStatus(status);

        String logCode = agentAllotBO.saveAgentAllot(data, toUserId);
        data.setLastAgentLog(logCode);
        buserBO.allotAgency(data);

        // add new agent allot log
        AgentAllot alData = new AgentAllot();
        alData.setApplyUser(userId);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(new Date());
        alData.setStatus(status);

        agentAllotBO.addAgentAllot(alData);

    }

    /*************** 忽略意向分配 **********************/
    @Override
    public void ignore(String userId, String aprrover) {
        BUser data = buserBO.getUser(userId);
        if (!(EUserStatus.MIND.getCode().equals(data.getStatus())
                || EUserStatus.ALLOTED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn0000", "该代理不是有意向代理");
        }

        data.setApprover(aprrover);
        data.setApproveDatetime(new Date());
        data.setStatus(EUserStatus.TO_MIND.getCode());
        String logCode = agentAllotBO.ignore(data);
        data.setLastAgentLog(logCode);
        buserBO.ignore(data);

        // add new agent allot log
        AgentAllot alData = new AgentAllot();
        alData.setApplyUser(userId);
        alData.setApplyLevel(data.getApplyLevel());
        alData.setApplyDatetime(new Date());
        alData.setStatus(EUserStatus.TO_MIND.getCode());
        agentAllotBO.addAgentAllot(alData);
    }

    /*************** 通过授权申请 **********************/
    @Override
    @Transactional
    public boolean approveImpower(String userId, String approver, String result,
            String remark) {
        BUser data = buserBO.getUser(userId);
        ImpowerApply log = impowerApplyBO
            .getImpowerApply(data.getLastAgentLog());
        if (!(EUserStatus.TO_APPROVE.getCode().equals(data.getStatus())
                || EUserStatus.TO_COMPANYAPPROVE.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        String fromUser = ESysUser.SYS_USER_BH.getCode();
        BUser highUser = buserBO.getSysUser();

        if (EResult.Result_YES.getCode().equals(result)) {
            // 更新上级
            /*
             * if (StringUtils.isNotBlank(log.getToUserId())) { highUser =
             * buserBO.getUser(log.getToUserId()); if
             * (!EUserKind.Plat.getCode().equals(highUser.getKind())) { fromUser
             * = highUser.getUserId(); } }
             */
            data.setHighUserId(highUser.getUserId());

            AgentImpower impower = agentImpowerBO
                .getAgentImpowerByLevel(data.getApplyLevel());

            if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
                if (StringUtils.isBlank(data.getIdNo())
                        || StringUtils.isBlank(data.getIdHand())) {
                    throw new BizException("xn0000", "本等级需要实名认证，该代理还未完成实名认证");
                }
            }
            // 需要公司授权
            if (EBoolean.YES.getCode().equals(impower.getIsCompanyImpower())
                    && !EUser.ADMIN.getCode().equals(approver)) {
                status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            } else {
                data.setLevel(data.getApplyLevel());
                // data.setImpowerDatetime(new Date());

                // 根据用户类型获取账户列表
                List<String> currencyList = distributeAccount(data.getUserId(),
                    data.getRealName(), EUserKind.Merchant.getCode(),
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
                // 分配账户
                accountBO.distributeAccount(data.getUserId(),
                    data.getRealName(), EAccountType.Business, currencyList,
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());

                // 介绍奖
                /*
                 * long amount = 0L; if
                 * (StringUtils.isNotBlank(data.getIntroducer())) { BUser buser
                 * = buserBO.getUser(data.getIntroducer()); Intro iData =
                 * introBO.getIntroByLevel(buser.getLevel(),
                 * data.getApplyLevel()); amount =
                 * AmountUtil.mul(impower.getMinCharge(), iData.getPercent() /
                 * 100); accountBO.transAmountCZB(fromUser,
                 * ECurrency.YJ_CNY.getCode(), buser.getUserId(),
                 * ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_JSJL, "介绍代理["
                 * + data.getRealName() + "]的" + EBizType.AJ_JSJL.getCode() +
                 * "支出", "介绍代理[" + data.getRealName() + "]的" +
                 * EBizType.AJ_JSJL.getValue() + "收入", data.getUserId()); }
                 */

                // 统计
                // reportBO.saveReport(data);
            }

            // 未通过，有推荐人
        } else if (StringUtils.isNotBlank(data.getUserReferee())) {
            status = EUserStatus.IMPOWERO_INFO.getCode();
        } else {
            status = EUserStatus.TO_MIND.getCode();
        }

        Date date = new Date();
        if (EUserStatus.IMPOWERED.getCode().equals(status)) {
            // data.setImpowerDatetime(date);
        }

        data.setApprover(approver);
        data.setApplyDatetime(date);
        data.setRemark(remark);
        data.setStatus(status);

        String logCode = impowerApplyBO.approveImpower(log, data);
        data.setLastAgentLog(logCode);
        buserBO.approveImpower(data);

        // insert new impower log
        ImpowerApply imData = new ImpowerApply();
        imData.setApplyUser(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(status);
        impowerApplyBO.addImpowerApply(imData);
        return true;

    }

    /*************** 通过升级申请 **********************/
    @Override
    @Transactional
    public void approveUpgrade(String userId, String approver, String remark,
            String result) {
        BUser data = buserBO.getUser(userId);
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
            AgentUpgrade auData = agentUpgradeBO
                .getAgentUpgradeByLevel(data.getApplyLevel());

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

        String logCode = uplevelApplyBO.approveUpgrade(data, status);
        data.setLastAgentLog(logCode);
        buserBO.approveUpgrade(data);

        // 新增升级申请记录
        UpLevelApply upData = new UpLevelApply();
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.addUplevelApply(upData);

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
        String logCode = uplevelApplyBO.approveCanenl(data, status);
        data.setLastAgentLog(logCode);
        buserBO.cancelUplevel(data);

        // 新增升级申请记录
        UpLevelApply upData = new UpLevelApply();
        upData.setApplyUser(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(EUserStatus.CANCELED.getCode());
        upData.setApplyDatetime(new Date());

        uplevelApplyBO.addUplevelApply(upData);
    }

    /*************** 通过取消审核申请 **********************/
    @Override
    public void approveImpowerCanenl(String userId, String approver,
            String result, String remark) {
        BUser data = buserBO.getUser(userId);
        if (!(EUserStatus.TO_CANCEL.getCode().equals(data.getStatus())
                || EUserStatus.TO_COMPANYCANCEL.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn000", "该代理未处于申请取消状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = EUserStatus.CANCELED.getCode();
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            // 账户清零
            accountBO.changeAmount(account.getAccountNumber(), EChannelType.NBZ,
                null, null, data.getUserId(), EBizType.AJ_QXSQ,
                EBizType.AJ_QXSQ.getValue(), -account.getAmount());

        }
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);
        String logCode = impowerApplyBO.cancelImpower(data, status);
        data.setLastAgentLog(logCode);
        buserBO.cancelImpower(data);

        // insert new impower log
        ImpowerApply imData = new ImpowerApply();
        imData.setApplyUser(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(EUserStatus.CANCELED.getCode());
        impowerApplyBO.addImpowerApply(imData);
    }

    /*************** 更改 **********************/
    @Override
    public void editManager(String userId, String manager, String updater) {
        BUser data = buserBO.getUser(userId);
        buserBO.getCheckUser(manager);
        buserBO.refreshManager(data, manager, updater);
    }

    private void changeHighUser(String highUserId, String userId,
            String approver, String remark) {

        List<BUser> list = buserBO.getUsersByUserReferee(userId);
        for (BUser buser : list) {
            Date date = new Date();
            buser.setHighUserId(userId);
            buser.setUpdater(approver);
            buser.setUpdateDatetime(date);

            buser.setRemark(remark);
            String logCode = agentAllotBO.refreshHighUser(buser);
            buser.setLastAgentLog(logCode);
            buserBO.refreshHighUser(buser);

            List<BUser> list2 = buserBO
                .getUsersByUserReferee(buser.getUserId());
            for (BUser user2 : list2) {
                changeHighUser(highUserId, user2.getUserId(), approver, remark);
            }

        }

    }

    private void changeAmount(BUser data) {
        BUser highUser = buserBO.getUser(data.getUserId());
        // 推荐人的上级门槛转入新上级
        List<BUser> list = buserBO.getUsersByUserReferee(data.getUserId());
        BUser oldHighUser = null;
        for (BUser buser : list) {
            // 被推荐代理门槛款
            Account refreeAccount = accountBO.getAccountByUser(
                buser.getUserId(), ECurrency.MK_CNY.getCode());
            Account account = accountBO.getAccountByUser(highUser.getUserId(),
                ECurrency.MK_CNY.getCode());
            oldHighUser = buserBO.getCheckUser(buser.getUserId());

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

            changeAmount(buser);

        }
    }

    // 分配账号
    private List<String> distributeAccount(String userId, String mobile,
            String kind, String companyCode, String systemCode) {
        List<String> currencyList = new ArrayList<String>();
        if (EUserKind.Customer.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
        } else if (EUserKind.Merchant.getCode().equals(kind)) {
            currencyList.add(ECurrency.YJ_CNY.getCode());
            currencyList.add(ECurrency.MK_CNY.getCode());
        }
        return currencyList;
    }

}
