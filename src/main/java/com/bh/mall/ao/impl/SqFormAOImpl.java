package com.bh.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.ISqFormAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAddressBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.IdCardChecker;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.domain.JsAward;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627251Req;
import com.bh.mall.dto.req.XN627362Req;
import com.bh.mall.dto.res.XN627303Res;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAddressType;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EOrderStatus;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;

@Service
public class SqFormAOImpl implements ISqFormAO {

    @Autowired
    private ISqFormBO sqFormBO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private IAgentAO agentAO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IAgentLevelBO agentLevelBO;

    @Autowired
    private IJsAwardBO jsAwardBO;

    @Autowired
    private IAgentReportBO agentReportBO;

    @Autowired
    private IOutOrderBO outOrderBO;

    @Autowired
    private IInnerOrderBO innerOrderBO;

    // 申请代理， 有推荐人
    @Override
    @Transactional
    public XN627303Res applyHaveUserReferee(XN627251Req req) {
        XN627303Res result = null;
        PhoneUtil.checkMobile(req.getMobile());
        agentBO.isMobileExist(req.getMobile());

        // 校验介绍人
        String introducer = req.getIntroducer();
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            Agent agent = agentBO.getAgentByMobile(req.getIntroducer());
            introducer = agent.getUserId();
            if (agent.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于介绍人哦！");
            }
        }

        // 是否需要实名制
        AgentLevel impower = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
            if (!idCardChecker.validate()) {
                throw new BizException("xn0000", "请输入正确的身份证号码");
            }
            agentBO.getAgentByIdNo(req.getIdNo());
        }

        Agent data = agentBO.getAgent(req.getUserId());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        String status = EUserStatus.TO_APPROVE.getCode(); // 待审核授权
        String toUser = data.getUserReferee();

        Agent userReferee = agentBO.getAgent(data.getUserReferee());
        data.setTeamName(userReferee.getTeamName());
        if (data.getApplyLevel() < userReferee.getLevel()) {
            throw new BizException("xn0000", "申请等级不能高于推荐代理的等级");
        }
        if (data.getApplyLevel() == userReferee.getLevel()) {
            toUser = userReferee.getHighUserId();
        }

        // 是否需要公司审核
        if (EBoolean.YES.getCode().equals(impower.getIsCompanyImpower())) {
            status = EUserStatus.TO_COMPANYAPPROVE.getCode();
        }

        // 申请最高等级
        if (EAgentLevel.ONE.getCode().equals(req.getApplyLevel())) {
            // 防止团队名称重复
            agentBO.checkTeamName(req.getTeamName());
            data.setTeamName(req.getTeamName());
        }

        // 申请等级不等于推荐人等级
        if (data.getApplyLevel() != userReferee.getLevel()) {
            data.setUserReferee(null);
            toUser = userReferee.getUserId();
        }

        SqForm sqData = new SqForm();
        sqData.setToUserId(toUser);
        sqData.setRealName(req.getRealName());
        sqData.setWxId(req.getWxId());
        sqData.setMobile(req.getMobile());
        sqData.setProvince(req.getProvince());
        sqData.setCity(req.getCity());

        sqData.setIdKind(req.getIdKind());
        sqData.setIdNo(req.getIdNo());
        sqData.setIdHand(req.getIdHand());

        sqData.setIntroducerMobile(introducer);
        sqData.setStatus(status);
        sqData.setArea(req.getArea());
        // data.setPayPdf(req.getPayPdf());

        data.setAddress(req.getAddress());

        sqFormBO.toApply(sqData);
        addressBO.saveAddress(sqData.getUserId(),
            EAddressType.User_Address.getCode(), req.getMobile(),
            req.getRealName(), req.getProvince(), req.getCity(), req.getArea(),
            req.getAddress(), EBoolean.YES.getCode());
        result = new XN627303Res(sqData.getUserId(), EBoolean.NO.getCode());
        return result;

    }

    @Override
    @Transactional
    public void approveSqFormByB(String userId, String approver, String result,
            String remark) {

        Agent applyAgent = agentBO.getAgent(userId);
        SqForm data = new SqForm();

        if (!EUserStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        String status = EUserStatus.NO_THROUGH.getCode();
        String fromUser = ESysUser.SYS_USER_BH.getCode();

        Agent highUser = agentBO.getAgent(data.getToUserId());
        // 审核通过
        if (EResult.Result_YES.getCode().equals(result)) {

            AgentLevel impower = agentLevelBO
                .getAgentByLevel(data.getApplyLevel());
            // 需要公司授权
            if (EBoolean.YES.getCode().equals(impower.getIsCompanyImpower())) {
                status = EUserStatus.TO_COMPANYAPPROVE.getCode();
            } else {
                data.setApplyLevel(data.getApplyLevel());
                status = EUserStatus.IMPOWERED.getCode();

                // 根据用户类型获取账户列表
                List<String> currencyList = distributeAccount(data.getUserId(),
                    data.getRealName(), EUserKind.Merchant.getCode());
                // 分配账户
                accountBO.distributeAccount(data.getUserId(),
                    data.getRealName(), EAccountType.Business, currencyList,
                    ESystemCode.BH.getCode(), ESystemCode.BH.getCode());

                // 介绍奖

                long amount = 0L;
                if (StringUtils.isNotBlank(applyAgent.getIntroducer())) {
                    Agent buser = agentBO.getAgent(applyAgent.getIntroducer());
                    JsAward iData = jsAwardBO.getJsAwardByLevel(
                        buser.getLevel(), data.getApplyLevel());
                    amount = AmountUtil.mul(impower.getMinCharge(),
                        iData.getPercent() / 100);
                    accountBO.transAmountCZB(fromUser,
                        ECurrency.YJ_CNY.getCode(), buser.getUserId(),
                        ECurrency.YJ_CNY.getCode(), amount, EBizType.AJ_JSJL,
                        "介绍代理[" + data.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getCode() + "支出",
                        "介绍代理[" + data.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getValue() + "收入",
                        data.getUserId());
                }

                // 统计
                agentReportBO.saveAgentReport(data, applyAgent);
            }
        }

        Date date = new Date();
        data.setApprover(approver);
        data.setApproveDatetime(date);
        data.setStatus(status);
        data.setRemark(remark);
        sqFormBO.approveSqForm(data);

    }

    @Override
    @Transactional
    public void approveSqFormByP(String userId, String approver, String result,
            String remark) {

        Agent applyAgent = agentBO.getAgent(userId);
        SqForm data = new SqForm();

        if (EUserStatus.TO_COMPANYAPPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        String status = EUserStatus.NO_THROUGH.getCode();
        String fromUser = ESysUser.SYS_USER_BH.getCode();

        Agent highUser = agentBO.getAgent(data.getToUserId());
        // 审核通过
        if (EResult.Result_YES.getCode().equals(result)) {
            status = EUserStatus.IMPOWERED.getCode();

            if (StringUtils.isNotBlank(data.getToUserId())) {
                highUser = agentBO.getAgent(data.getToUserId());
                if (!EUserKind.Plat.getCode().equals(highUser.getKind())) {
                    fromUser = highUser.getUserId();
                }
            }
            // data.setHighUserId(highUser.getUserId());

            AgentLevel impower = agentLevelBO
                .getAgentByLevel(data.getApplyLevel());

            if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
                if (StringUtils.isBlank(data.getIdNo())
                        || StringUtils.isBlank(data.getIdHand())) {
                    throw new BizException("xn0000", "本等级需要实名认证，该代理还未完成实名认证");
                }
            }

            data.setApplyLevel(data.getApplyLevel());
            // data.setImpowerDatetime(new Date());

            // 根据用户类型获取账户列表
            List<String> currencyList = distributeAccount(data.getUserId(),
                data.getRealName(), EUserKind.Merchant.getCode());
            // 分配账户
            accountBO.distributeAccount(data.getUserId(), data.getRealName(),
                EAccountType.Business, currencyList, ESystemCode.BH.getCode(),
                ESystemCode.BH.getCode());

            // 介绍奖

            long amount = 0L;
            if (StringUtils.isNotBlank(applyAgent.getIntroducer())) {
                Agent buser = agentBO.getAgent(applyAgent.getIntroducer());
                JsAward iData = jsAwardBO.getJsAwardByLevel(buser.getLevel(),
                    data.getApplyLevel());
                amount = AmountUtil.mul(impower.getMinCharge(),
                    iData.getPercent() / 100);
                accountBO.transAmountCZB(fromUser, ECurrency.YJ_CNY.getCode(),
                    buser.getUserId(), ECurrency.YJ_CNY.getCode(), amount,
                    EBizType.AJ_JSJL,
                    "介绍代理[" + data.getRealName() + "]的"
                            + EBizType.AJ_JSJL.getCode() + "支出",
                    "介绍代理[" + data.getRealName() + "]的"
                            + EBizType.AJ_JSJL.getValue() + "收入",
                    data.getUserId());
            }

            // 统计
            agentReportBO.saveAgentReport(data, applyAgent);

        }

        Date date = new Date();

        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(date);
        data.setApplyDatetime(date);
        data.setRemark(remark);
        sqFormBO.approveSqForm(data);

    }

    // 取消授权
    @Override
    public void cancelSqForm(String userId, String approver, String result,
            String remark) {
        agentBO.getAgent(userId);

        SqForm data = new SqForm();
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
        sqFormBO.cancelSqForm(data);
    }

    // 补全授权所需资料
    @Override
    public void addInfo(XN627362Req req) {

        agentBO.getAgent(req.getUserId());
        SqForm data = new SqForm();

        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            Agent introducer = agentBO.getAgentByMobile(req.getIntroducer());
            if (introducer.getUserId().equals(req.getUserId())) {
                throw new BizException("xn0000", "推荐人不能填自己哦！");
            }
            if (!EUserKind.Merchant.getCode().equals(introducer.getKind())) {
                throw new BizException("xn0000", "您填写的推荐人不是我们的代理哦！");
            }

            if (introducer.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于介绍人哦！");
            }
        }

        agentBO.checkTeamName(req.getTeamName());

        // 校验身份证
        AgentLevel impower = agentLevelBO.getAgentByLevel(data.getApplyLevel());
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
            if (!idCardChecker.validate()) {
                throw new BizException("xn0000", "请输入正确的身份证号码");
            }
            agentBO.getAgentByIdNo(req.getIdNo());
        }

        data.setUserId(req.getUserId());
        data.setRealName(data.getRealName());
        data.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        data.setIdKind(req.getIdKind());
        data.setIdNo(req.getIdNo());
        data.setIdHand(req.getIdHand());
        data.setIntroducerMobile(req.getIntroducer());
        sqFormBO.addInfo(data);
    }

    // 列表查询
    @Override
    public List<SqForm> querySqFormList(SqForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        List<SqForm> list = sqFormBO.querySqFormList(condition);
        for (SqForm sqForm : list) {
            Agent buser = agentAO.getAgent(sqForm.getUserId());
            sqForm.setUser(buser);
        }
        return list;
    }

    // 详细查询
    @Override
    public SqForm getSqForm(String code) {
        SqForm data = sqFormBO.getSqForm(code);
        Agent buser = agentAO.getAgent(data.getUserId());
        data.setUser(buser);
        return data;

    }

    // 分页查询
    @Override
    public Paginable<SqForm> querySqFormPage(int start, int limit,
            SqForm condition) {

        if (condition.getApplyDatetimeStart() != null
                && condition.getApplyDatetimeEnd() != null
                && condition.getApplyDatetimeStart()
                    .after(condition.getApplyDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }

        Paginable<SqForm> page = sqFormBO.getPaginable(start, limit, condition);
        List<SqForm> list = page.getList();

        for (SqForm sqForm : list) {
            Agent agent = agentAO.getAgent(sqForm.getUserId());
            sqForm.setUser(agent);
        }
        page.setList(list);
        return page;
    }

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

    @Override
    public void toQuit(String userId) {
        Agent agent = agentBO.getAgent(userId);

        // 是否有下级
        Agent uCondition = new Agent();
        uCondition.setHighUserId(agent.getUserId());
        List<Agent> list = agentBO.queryAgentList(uCondition);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn000", "您还有下级，无法申请退出");
        }

        // 可提现账户是否余额
        Account txAccount = accountBO.getAccountByUser(agent.getUserId(),
            ECurrency.YJ_CNY.getCode());
        if (txAccount.getAmount() > 0) {
            throw new BizException("xn000", "您的可提现账户中还有余额，请取出后再申请退出");
        }

        // 是否有未完成的订单
        OutOrder oCondition = new OutOrder();
        oCondition.setApplyUser(agent.getUserId());
        oCondition.setStatusForQuery(EOrderStatus.NO_CallOFF.getCode());
        long count = outOrderBO.selectCount(oCondition);
        if (count != 0) {
            throw new BizException("xn000", "您还有未完成的订单,请在订单完成后申请");
        }

        InnerOrder ioCondition = new InnerOrder();
        ioCondition.setApplyUser(agent.getUserId());
        ioCondition.setStatusForQuery(EOrderStatus.NO_CallOFF.getCode());
        long ioCount = innerOrderBO.selectCount(ioCondition);
        if (ioCount != 0) {
            throw new BizException("xn000", "您还有未完成的内购订单,请在订单完成后申请");
        }

    }

}
