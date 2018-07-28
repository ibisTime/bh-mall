package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.ao.ISqFormAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAfterSaleBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IBuserBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IOrderBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.BUser;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.Order;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627303Res;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EAfterSaleStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUser;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SqFormAOImpl implements ISqFormAO {

    @Autowired
    private ISqFormBO impowerApplyBO;

    @Autowired
    private IBuserBO buserBO;

    @Autowired
    private IBuserAO buserAO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IInnerOrderBO innerOrderBO;

    @Autowired
    private IAfterSaleBO afterSaleBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    /*************** 代理申请，有推荐人 **********************/
    // 申请代理， 有推荐人
    @Override
    @Transactional
    public XN627303Res applyHaveUserReferee(XN627251Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        String introducer = req.getIntroducer();
        // 校验介绍人
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            BUser buser = buserBO.getUserByMobile(req.getIntroducer());
            introducer = buser.getUserId();
            if (buser.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于推荐人哦！");
            }
        }
        // 校验手机号
        buserBO.isMobileExist(req.getMobile());
        // 校验身份证
        if (StringUtils.isNotBlank(req.getIdNo())) {
            buserBO.getUserByIdNo(req.getIdNo());
        }

        XN627303Res result = null;
        // 是否可被意向
        AgentLevel impower = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));

        if (EBoolean.NO.getCode().equals(impower.getIsIntent())) {
            throw new BizException("xn0000", "本等级不可被意向");
        }
        // 是否需要实名制
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            if (StringUtils.isBlank(req.getIdNo())
                    || StringUtils.isBlank(req.getIdHand())) {
                throw new BizException("xn0000", "本等级需要实名认证，请完成实名认证");
            }
        }

        BUser data = buserBO.getUser(req.getUserId());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        String status = EUserStatus.TO_APPROVE.getCode(); // 待审核授权
        String toUser = data.getUserReferee();

        BUser userReferee = buserBO.getUser(data.getUserReferee());
        data.setTeamName(userReferee.getTeamName());
        if (data.getApplyLevel() < userReferee.getLevel()) {
            throw new BizException("xn0000", "申请等级不能高于推荐代理的等级");
        }
        if (data.getApplyLevel() == userReferee.getLevel()) {
            toUser = userReferee.getHighUserId();

        }
        // 是否需要公司审核
        if (1 == data.getApplyLevel()) {
            status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            // 防止团队名称重复
            buserBO.checkTeamName(req.getTeamName());
            data.setTeamName(req.getTeamName());
        }
        if (data.getApplyLevel() > userReferee.getLevel()) {
            data.setUserReferee(null);
            toUser = userReferee.getUserId();
        }
        System.out.println("toUser:" + toUser);
        System.out.println("UserReferee:" + data.getUserReferee());

        SqForm sqData = new SqForm();
        sqData.setRealName(req.getRealName());
        sqData.setWxId(req.getWxId());
        sqData.setMobile(req.getMobile());
        sqData.setProvince(req.getProvince());
        sqData.setCity(req.getCity());

        sqData.setIdKind(req.getIdKind());
        sqData.setIdNo(req.getIdNo());
        sqData.setIdHand(req.getIdHand());

        // sqData.setIntroducer(introducer);
        sqData.setStatus(status);
        sqData.setArea(req.getArea());
        // data.setPayPdf(req.getPayPdf());

        data.setAddress(req.getAddress());
        data.setSource(req.getFromInfo());

        // String logCode = agentAllotBO.toApply(data, toUser,
        // EUserStatus.TO_APPROVE.getCode());
        // data.setLastAgentLog(logCode);

        impowerApplyBO.toApply(sqData);
        addressBO.saveAddress(sqData.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
        result = new XN627303Res(sqData.getUserId(), EBoolean.NO.getCode());

        // insert new agent allot log
        SqForm alData = new SqForm();
        alData.setUserId(req.getUserId());
        alData.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        alData.setApplyDatetime(new Date());
        alData.setStatus(EUserStatus.MIND.getCode());

        impowerApplyBO.addImpowerApply(alData);
        return result;

    }

    /*************** 通过授权申请 **********************/
    @Override
    @Transactional
    public boolean approveImpower(String userId, String approver,
            String remark) {
        BUser data = buserBO.getUser(userId);
        SqForm log = impowerApplyBO.getImpowerApply(data.getLastAgentLog());
        if (!(EUserStatus.TO_APPROVE.getCode().equals(data.getStatus())
                || EUserStatus.TO_COMPANYAPPROVE.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        String fromUser = ESysUser.SYS_USER_BH.getCode();
        BUser highUser = buserBO.getSysUser();

        if (EResult.Result_YES.getCode().equals(' ')) {
            // 更新上级
            /*
             * if (StringUtils.isNotBlank(log.getToUserId())) { highUser =
             * buserBO.getUser(log.getToUserId()); if
             * (!EUserKind.Plat.getCode().equals(highUser.getKind())) { fromUser
             * = highUser.getUserId(); } }
             */
            data.setHighUserId(highUser.getUserId());

            AgentLevel impower = agentLevelBO
                .getAgentByLevel(data.getApplyLevel());

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
                    data.getRealName(), EUserKind.Merchant.getCode());
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

        // insert new impower log
        SqForm imData = new SqForm();
        imData.setUserId(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(status);
        impowerApplyBO.approveImpower(imData);
        return true;

    }

    /*************** 取消授权申请 **********************/
    @Override
    public void cancelImpower(String userId) {
        BUser data = buserBO.getUser(userId);

        // 是否有下级
        BUser uCondition = new BUser();
        uCondition.setHighUserId(data.getUserId());
        List<BUser> list = buserBO.queryUserList(uCondition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn000", "您还有下级，无法申请退出");
        }

        // 可提现账户是否余额
        Account txAccount = accountBO.getAccountByUser(data.getUserId(),
            ECurrency.YJ_CNY.getCode());
        if (txAccount.getAmount() > 0) {
            throw new BizException("xn000", "您的可提现账户中还有余额，请取出后再申请退出");
        }

        // 是否有未完成的订单
        Order oCondition = new Order();
        oCondition.setApplyUser(data.getUserId());
        oCondition.setStatusForQuery(EOrderStatus.NO_CallOFF.getCode());
        long count = orderBO.selectCount(oCondition);
        if (count != 0) {
            throw new BizException("xn000", "您还有未完成的订单,请在订单完成后申请");
        }

        InnerOrder ioCondition = new InnerOrder();
        ioCondition.setApplyUser(data.getUserId());
        ioCondition.setStatusForQuery(EOrderStatus.NO_CallOFF.getCode());
        long ioCount = innerOrderBO.selectCount(ioCondition);
        if (ioCount != 0) {
            throw new BizException("xn000", "您还有未完成的内购订单,请在订单完成后申请");
        }

        AfterSale asCondition = new AfterSale();
        asCondition.setApplyUser(data.getUserId());
        asCondition.setStatus(EAfterSaleStatus.NO_CallOff.getCode());
        long asCount = afterSaleBO.selectCount(asCondition);
        if (asCount != 0) {
            throw new BizException("xn000", "您还有未完成的售后单,请在订单完成后申请");
        }

        String status = EUserStatus.TO_COMPANYCANCEL.getCode();
        if (StringUtils.isNotBlank(data.getHighUserId())) {
            BUser buser = buserBO.getUser(data.getHighUserId());
            if (!EUserKind.Plat.getCode().equals(buser.getKind())) {
                status = EUserStatus.TO_CANCEL.getCode();
            }
        }
        // insert new impower log
        SqForm imData = new SqForm();
        imData.setUserId(userId);
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(EUserStatus.TO_CANCEL.getCode());
        impowerApplyBO.cancelImpower(imData);
    }

    /*************** 通过取消审核申请 **********************/
    @Override
    public void approveImpowerCanenl(String userId, String approver,
            String remark) {
        BUser data = buserBO.getUser(userId);
        if (!(EUserStatus.TO_CANCEL.getCode().equals(data.getStatus())
                || EUserStatus.TO_COMPANYCANCEL.getCode()
                    .equals(data.getStatus()))) {
            throw new BizException("xn000", "该代理未处于申请取消状态");
        }

        String status = EUserStatus.IMPOWERED.getCode();
        if (EResult.Result_YES.getCode().equals(' ')) {
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

        // insert new impower log
        SqForm imData = new SqForm();
        imData.setUserId(userId);
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(EUserStatus.CANCELED.getCode());
        impowerApplyBO.addImpowerApply(imData);
    }

    /***************   补全信息 **********************/
    // 补全授权所需资料
    @Override
    public void addInfo(XN627362Req req) {
        BUser data = buserBO.getUser(req.getUserId());
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            BUser user = buserBO.getUserByMobile(req.getIntroducer());
            if (user.getUserId().equals(req.getUserId())) {
                throw new BizException("xn0000", "推荐人不能填自己哦！");
            }
            if (!EUserKind.Merchant.getCode().equals(user.getKind())) {
                throw new BizException("xn0000", "您填写的推荐人不是我们的代理哦！");
            }

            if (user.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于介绍人哦！");
            }
        }

        buserBO.checkTeamName(req.getTeamName());

        // 校验身份证
        if (StringUtils.isNotBlank(req.getIdNo())) {
            buserBO.getUserByIdNo(req.getIdNo());
        }

        AgentLevel impower = agentLevelBO.getAgentByLevel(data.getApplyLevel());
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            if (StringUtils.isBlank(req.getIdNo())
                    || StringUtils.isBlank(req.getIdHand())) {
                throw new BizException("xn0000", "本等级需要实名认证，请完成实名认证");
            } else {
                buserBO.getUserByIdNo(req.getIdNo());
            }
        }

        // data.setRealName(req.getRealName());
        // data.setWxId(req.getWxId());
        // data.setMobile(req.getMobile());
        // data.setProvince(req.getProvince());
        // data.setCity(req.getCity());
        //
        // data.setArea(req.getArea());
        // data.setAddress(req.getAddress());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setTeamName(req.getTeamName());

        data.setIdKind(req.getIdKind());
        data.setIdNo(req.getIdNo());
        data.setIdHand(req.getIdHand());
        data.setIntroducer(req.getIntroducer());

        // 需添加等级以及to user审核
        // get last agency log
        // check approver
        // if kind equals b - to approve
        // if kind equals p - to company approve

        // insert new impower log
        SqForm imData = new SqForm();
        imData.setUserId(req.getUserId());
        imData.setApplyLevel(data.getApplyLevel());
        imData.setApplyDatetime(new Date());
        // imData.setPayAmount(payAmount);
        // imData.setPaymentPdf(payPdf);
        imData.setStatus(EUserStatus.TO_COMPANYAPPROVE.getCode());
        impowerApplyBO.addInfo(imData);
    }

    /*********************** 查询 *************************/

    @Override
    public List<SqForm> queryImpowerApplyList(SqForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<SqForm> list = impowerApplyBO.queryAgencyLogList(condition);
        for (SqForm agencyLog : list) {
            BUser userReferee = null;
            BUser buser = buserAO.doGetUser(agencyLog.getUserId());
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
    public SqForm getImpowerApply(String code) {
        SqForm data = impowerApplyBO.getImpowerApply(code);
        BUser buser = buserAO.doGetUser(data.getUserId());
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
    public Paginable<SqForm> queryIntentionAgentFrontPage(int start, int limit,
            SqForm condition) {

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

        Paginable<SqForm> page = impowerApplyBO.getPaginable(start, limit,
            condition);
        BUser userReferee = null;
        BUser buser = null;
        for (Iterator<SqForm> iterator = page.getList().iterator(); iterator
            .hasNext();) {
            SqForm agencyLog = iterator.next();
            buser = buserAO.doGetUser(agencyLog.getUserId());
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
    public String addImpowerApply(SqForm data) {
        // insert new data
        impowerApplyBO.addImpowerApply(data);
        return null;
    }

    /*********************** 查询是否需要补全金额 *************************/
    @Override
    public Paginable<SqForm> queryImpowerApplyPage(int start, int limit,
            SqForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<SqForm> page = impowerApplyBO.getPaginable(start, limit,
            condition);
        List<SqForm> list = page.getList();

        for (SqForm impowerApply : list) {
            BUser userReferee = null;
            BUser buser = buserAO.doGetUser(impowerApply.getUserId());
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
                AgentLevel agent = agentLevelBO
                    .getAgentByLevel(buser.getApplyLevel());
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

    /*************** 分配账号**********************/
    // 分配账号
    private List<String> distributeAccount(String userId, String mobile,
            String kind) {
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
