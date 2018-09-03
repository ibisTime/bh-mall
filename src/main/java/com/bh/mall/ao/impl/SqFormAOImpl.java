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
import com.bh.mall.bo.IAgentLogBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.IInnerOrderBO;
import com.bh.mall.bo.IJsAwardBO;
import com.bh.mall.bo.IOutOrderBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISqFormBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.IYxFormBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.IdCardChecker;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Address;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentLog;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.domain.JsAward;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627270Req;
import com.bh.mall.dto.req.XN627271Req;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentLogType;
import com.bh.mall.enums.EAgentStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.EResult;
import com.bh.mall.enums.ESqFormStatus;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class SqFormAOImpl implements ISqFormAO {

    @Autowired
    private ISqFormBO sqFormBO;

    @Autowired
    private IAgentBO agentBO;

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

    @Autowired
    private IYxFormBO yxFormBO;

    @Autowired
    private IAgentLogBO agentLogBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IWareBO wareBO;

    @Autowired
    private IAgentAO agentAO;

    /**
     * 有头的代理申请授权
     * 1、校验介绍人、身份证、团队名称
     * 2、根据申请等级判断两个代理的关系
     * @see com.bh.mall.ao.ISqFormAO#applyHaveUserReferee(com.bh.mall.dto.req.XN627270Req)
     */
    @Override
    @Transactional
    public void applyHaveUserReferee(XN627270Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        agentBO.isMobileExist(req.getMobile());

        // 是够有介绍人并校验
        String introducer = req.getIntroducer();
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            Agent introAgent = agentBO.getAgentByMobile(req.getIntroducer());
            introducer = introAgent.getUserId();

            if (!(EAgentStatus.IMPOWERED.getCode()
                .equals(introAgent.getStatus())
                    || EAgentStatus.TO_UPGRADE.getCode()
                        .equals(introAgent.getStatus())
                    || EAgentStatus.UPGRADE_COMPANY.getCode()
                        .equals(introAgent.getStatus())
                    || EAgentStatus.TO_UPGRADE.getCode()
                        .equals(introAgent.getStatus()))) {
                throw new BizException("xn0000", "该介绍人还未授权哦！");
            }
            if (introAgent.getUserId().equals(req.getUserId())) {
                throw new BizException("xn0000", "介绍人不能填自己哦！");
            }
            if (introAgent.getLevel() < StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于介绍人哦！");
            }
        }

        // 是否需要实名制并校验
        AgentLevel impower = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));
        if (EBoolean.YES.getCode().equals(impower.getIsRealName())) {
            IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
            if (!idCardChecker.validate()) {
                throw new BizException("xn0000", "请输入正确的身份证号码");
            }
            agentBO.getAgentByIdNo(req.getIdNo());
        }

        // 分享二维码的代理
        Agent agent = agentBO.getAgent(req.getUserId());
        Agent fromAgent = agentBO.getAgent(agent.getFromUserId());
        if (StringValidater.toInteger(req.getApplyLevel()) < fromAgent
            .getLevel()) {
            throw new BizException("xn0000", "申请等级不能高于推荐代理的等级");
        }

        // 等级与所扫码代理等级相同，分享二维码视为代理推荐人
        String userRefree = null;
        String toUserId = null;
        if (StringValidater.toInteger(req.getApplyLevel()) == fromAgent
            .getLevel()) {
            userRefree = fromAgent.getUserId();
            toUserId = fromAgent.getHighUserId();
        } else {
            toUserId = fromAgent.getUserId();
        }

        // 申请最高等级
        String status = ESqFormStatus.TO_APPROVE.getCode();
        if (EAgentLevel.ONE.getCode().equals(req.getApplyLevel())) {
            // 校验团队名称
            agentBO.checkTeamName(req.getTeamName());
            agent.setTeamName(req.getTeamName());
            // 直接由公司审核
            status = ESqFormStatus.COMPANY_IMPOWER.getCode();
        }

        // 新增授权单
        SqForm sqForm = sqFormBO.getSqForm(agent.getUserId());
        if (null == sqForm) {
            sqForm = sqFormBO.applySqForm(agent.getUserId(), req.getRealName(),
                req.getMobile(), req.getWxId(), req.getApplyLevel(), toUserId,
                req.getTeamName(), introducer, userRefree, req.getIdKind(),
                req.getIdNo(), req.getIdHand(), req.getProvince(),
                req.getCity(), req.getArea(), req.getAddress(), status);
        } else {
            sqForm = sqFormBO.refreshSqForm(sqForm, req.getRealName(),
                req.getMobile(), req.getWxId(), req.getApplyLevel(), toUserId,
                req.getTeamName(), introducer, userRefree, req.getIdKind(),
                req.getIdNo(), req.getIdHand(), req.getProvince(),
                req.getCity(), req.getArea(), req.getAddress(), status);
        }

        String logCode = agentLogBO.applySqForm(sqForm,
            EAgentLogType.Imporder.getCode());

        agentBO.refreshAgent(sqForm, logCode, status);
    }

    /**
     * 意向代理通过之后，补充授权所需信息
     */
    @Override
    public void addInfo(XN627271Req req) {

        Agent agent = agentBO.getAgent(req.getUserId());
        // 校验介绍人
        String introducer = req.getIntroducer();
        if (StringUtils.isNotBlank(req.getIntroducer())) {
            PhoneUtil.checkMobile(req.getIntroducer());
            Agent intrAgent = agentBO.getAgentByMobile(req.getIntroducer());
            introducer = intrAgent.getUserId();

            if (!ESqFormStatus.IMPOWERED.getCode()
                .equals(intrAgent.getStatus())) {
                throw new BizException("xn0000", "该介绍人还未授权哦！");
            }
            if (agent.getUserId().equals(introducer)) {
                throw new BizException("xn0000", "介绍人不能填自己哦！");
            }
            if (intrAgent.getLevel() <= StringValidater
                .toInteger(req.getApplyLevel())) {
                throw new BizException("xn0000", "您申请的等级需高于介绍人哦！");
            }
        }
        AgentLog log = agentLogBO.getAgentLog(agent.getLastAgentLog());
        String toUserId = log.getToUserId();

        // 申请最高等级
        String status = ESqFormStatus.TO_APPROVE.getCode();
        if (EAgentLevel.ONE.getCode().equals(req.getApplyLevel())) {
            // 校验团队名称
            agentBO.checkTeamName(req.getTeamName());
            agent.setTeamName(req.getTeamName());
            // 直接由公司审核
            status = ESqFormStatus.COMPANY_IMPOWER.getCode();
            SYSUser sysUser = sysUserBO.getSYSUser();
            toUserId = sysUser.getUserId();
        }

        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(req.getApplyLevel()));
        // 校验身份证
        if (EBoolean.YES.getCode().equals(agentLevel.getIsRealName())) {
            IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
            if (!idCardChecker.validate()) {
                throw new BizException("xn0000", "请输入正确的身份证号码");
            }
            agentBO.getAgentByIdNo(req.getIdNo());
        }

        SqForm sqForm = sqFormBO.getSqForm(agent.getUserId());
        if (null == sqForm) {
            sqForm = sqFormBO.applySqForm(agent.getUserId(),
                agent.getRealName(), agent.getMobile(), agent.getWxId(),
                req.getApplyLevel(), toUserId, req.getTeamName(), introducer,
                null, req.getIdKind(), req.getIdNo(), req.getIdHand(),
                agent.getProvince(), agent.getCity(), agent.getArea(),
                agent.getAddress(), status);
        } else {
            sqFormBO.refreshSqForm(sqForm, agent.getRealName(),
                agent.getMobile(), agent.getWxId(), req.getApplyLevel(),
                toUserId, req.getTeamName(), introducer, null, req.getIdKind(),
                req.getIdNo(), req.getIdHand(), agent.getProvince(),
                agent.getCity(), agent.getArea(), agent.getAddress(), status);
        }

        String logCode = agentLogBO.applySqForm(sqForm,
            EAgentLogType.Imporder.getCode());
        agentBO.addInfo(sqForm, logCode, status);
    }

    /**
     * 上级审核新申请代理，
     * 1、审核通过且无需公司审核，发放介绍奖（一次性），分配账户，新增默认地址，并确定关系
     * 
     */
    @Override
    @Transactional
    public void approveSqFormByB(String userId, String approver, String result,
            String remark) {

        SqForm sqForm = sqFormBO.getSqForm(userId);
        if (!ESqFormStatus.TO_APPROVE.getCode().equals(sqForm.getStatus())) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        Agent approveAgent = agentBO.getAgent(approver);
        // 审核通过
        String manager = null;
        String status = ESqFormStatus.CANCELED.getCode();
        Date date = null;
        if (EResult.Result_YES.getCode().equals(result)) {
            AgentLevel impower = agentLevelBO
                .getAgentByLevel(sqForm.getApplyLevel());
            // 需要公司授权
            if (EBoolean.YES.getCode().equals(impower.getIsCompanyImpower())) {
                status = ESqFormStatus.COMPANY_IMPOWER.getCode();
            } else {
                date = new Date();

                status = ESqFormStatus.IMPOWERED.getCode();
                manager = approveAgent.getManager();
                this.approveSqForm(sqForm);
                this.payAward(sqForm);
            }
        } else {
            // 未通过，清空手机号等信息，防止重新申请时重复
            Agent agent = agentBO.getAgent(userId);
            agentBO.resetInfo(agent);
        }

        String logCode = sqFormBO.approveSqForm(sqForm, approver,
            approveAgent.getRealName(), remark, status);
        // 记录日志
        Agent agent = agentBO.getAgent(sqForm.getUserId());
        agentBO.refreshSq(agent, sqForm, manager, sqForm.getToUserId(),
            sqForm.getTeamName(), sqForm.getApplyLevel(), status, approver,
            approveAgent.getRealName(), logCode, date);

    }

    /**
     * 审核新申请代理，
     * 1、审核通过，发放介绍奖（一次性），分配账户，新增默认地址，并确定关系
     */
    @Override
    @Transactional
    public void approveSqFormByP(String userId, String manager, String approver,
            String result, String remark) {

        SqForm sqForm = sqFormBO.getSqForm(userId);
        if (!ESqFormStatus.COMPANY_IMPOWER.getCode()
            .equals(sqForm.getStatus())) {
            throw new BizException("xn000", "该代理未处于待授权状态");
        }

        // 审核通过
        Date date = null;
        String status = ESqFormStatus.CANCELED.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = ESqFormStatus.IMPOWERED.getCode();
            // 为一级代理绑定管理员
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == sqForm
                .getApplyLevel()) {
                if (StringUtils.isBlank(manager)) {
                    throw new BizException("xn000", "请给该代理关联一个管理员");
                }
            }
            date = new Date();

            this.approveSqForm(sqForm);
            this.payAward(sqForm);
        } else {
            // 未通过，清空手机号等信息，防止重新申请时重复
            Agent agent = agentBO.getAgent(userId);
            if (StringUtils.isNotBlank(agent.getFromUserId())) {
                agent.setStatus(EAgentStatus.IMPOWERO_INFO.getCode());
            } else {
                agent.setStatus(EAgentStatus.ADD_INFO.getCode());
            }

            agentBO.resetInfo(agent);

        }
        // 记录日志
        SYSUser sysUser = sysUserBO.getSYSUser(approver);
        String logCode = sqFormBO.approveSqForm(sqForm, approver,
            sysUser.getRealName(), remark, status);

        Agent agent = agentBO.getAgent(sqForm.getUserId());
        agentBO.refreshSq(agent, sqForm, manager, sqForm.getToUserId(),
            sqForm.getTeamName(), sqForm.getApplyLevel(), status, approver,
            sysUser.getRealName(), logCode, date);

    }

    /**
     * 审核退出，无需公司审核，清空账户
     */
    @Override
    @Transactional
    public void cancelSqFormByB(String userId, String approver, String result,
            String remark) {
        SqForm sqForm = sqFormBO.getSqForm(userId);
        Agent agent = agentBO.getAgent(userId);

        if (!ESqFormStatus.TO_CANCEL.getCode().equals(sqForm.getStatus())) {
            throw new BizException("xn000", "该代理未申请退出状态");
        }

        String status = ESqFormStatus.IMPOWERED.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = ESqFormStatus.CANCELED.getCode();
            AgentLevel agentLevel = agentLevelBO
                .getAgentByLevel(agent.getLevel());
            // 是否需要公司审核
            if (EBoolean.YES.getCode()
                .equals(agentLevel.getIsCompanyImpower())) {
                status = ESqFormStatus.CANCEL_COMPANY.getCode();
            } else {
                Account account = accountBO.getAccountByUser(agent.getUserId(),
                    ECurrency.MK_CNY.getCode());
                // 账户清零
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, agent.getUserId(),
                    EBizType.AJ_QXSQ, EBizType.AJ_QXSQ.getValue(),
                    -account.getAmount());

                Account cAccount = accountBO.getAccountByUser(agent.getUserId(),
                    ECurrency.C_CNY.getCode());
                // 账户清零
                accountBO.changeAmount(cAccount.getAccountNumber(),
                    EChannelType.NBZ, null, null, agent.getUserId(),
                    EBizType.AJ_QXSQ, EBizType.AJ_QXSQ.getValue(),
                    -cAccount.getAmount());

                agent.setStatus(EAgentStatus.MIND.getCode());

                // 清空手机号等信息，防止重新申请时重复
                agentBO.resetInfo(agent);

                // 清空推荐关系
                agentBO.resetUserReferee(agent.getUserId());

                wareBO.removeByAgent(agent.getUserId());
            }
        }
        // 记录日志
        Agent approveAgent = agentBO.getAgent(approver);
        String logCode = sqFormBO.approveSqForm(sqForm, approver,
            approveAgent.getRealName(), remark, status);

        agentBO.refreshSq(agent, sqForm, agent.getManager(),
            agent.getHighUserId(), agent.getTeamName(), agent.getLevel(),
            status, approver, approveAgent.getRealName(), logCode,
            agent.getImpowerDatetime());

    }

    /**
     * 审核退出，清空账户
     * @see com.bh.mall.ao.ISqFormAO#cancelSqFormByB(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public void cancelSqFormByP(String userId, String approver, String result,
            String remark) {
        Agent agent = agentBO.getAgent(userId);
        SqForm data = sqFormBO.getSqForm(userId);

        if (!ESqFormStatus.CANCEL_COMPANY.getCode().equals(data.getStatus())) {
            throw new BizException("xn000", "该代理未申请退出");
        }

        String status = ESqFormStatus.IMPOWERED.getCode();
        if (EResult.Result_YES.getCode().equals(result)) {
            status = ESqFormStatus.CANCELED.getCode();
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            // 账户清零
            accountBO.changeAmount(account.getAccountNumber(), EChannelType.NBZ,
                null, null, data.getUserId(), EBizType.AJ_QXSQ,
                EBizType.AJ_QXSQ.getValue(), -account.getAmount());

            Account cAccount = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.C_CNY.getCode());
            // 账户清零
            accountBO.changeAmount(cAccount.getAccountNumber(),
                EChannelType.NBZ, null, null, data.getUserId(),
                EBizType.AJ_QXSQ, EBizType.AJ_QXSQ.getValue(),
                -cAccount.getAmount());

            wareBO.removeByAgent(agent.getUserId());

            // 清空手机号等信息，防止重新申请时重复
            agentBO.resetInfo(agent);

            // 清空推荐关系
            agentBO.resetUserReferee(agent.getUserId());

        }
        sqFormBO.cancelSqForm(data, status);

        // 记录日志
        SYSUser sysUser = sysUserBO.getSYSUser(approver);
        String logCode = sqFormBO.approveSqForm(data, approver,
            sysUser.getRealName(), remark, status);

        agentBO.refreshSq(agent, data, agent.getManager(),
            agent.getHighUserId(), agent.getTeamName(), agent.getLevel(),
            status, approver, sysUser.getRealName(), logCode,
            agent.getImpowerDatetime());

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
        for (SqForm data : list) {
            Agent buser = agentBO.getAgent(data.getUserId());
            data.setUser(buser);
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != data
                .getApplyLevel()
                    && StringUtils.isNotBlank(data.getToUserId())) {
                Agent toUser = agentBO.getAgent(data.getToUserId());
                data.setToUserName(toUser.getRealName());
            } else if (StringUtils.isNotBlank(data.getToUserId())) {
                SYSUser sysUser = sysUserBO.getSYSUser(data.getToUserId());
                data.setToUserName(sysUser.getRealName());
            }
        }
        return list;
    }

    // 详细查询
    @Override
    public SqForm getSqForm(String code) {
        SqForm data = sqFormBO.getSqForm(code);
        Agent buser = agentBO.getAgent(data.getUserId());
        data.setUser(buser);
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != data
            .getApplyLevel() && StringUtils.isNotBlank(data.getToUserId())) {
            Agent toUser = agentBO.getAgent(data.getToUserId());
            data.setToUserName(toUser.getRealName());
        } else if (StringUtils.isNotBlank(data.getToUserId())) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getToUserId());
            data.setToUserName(sysUser.getRealName());
        }
        AgentLevel agentLevel = agentLevelBO
            .getAgentByLevel(data.getApplyLevel());
        data.setImpowerAmount(agentLevel.getMinCharge());
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

        for (SqForm sqForm : page.getList()) {
            Agent agent = agentBO.getAgent(sqForm.getUserId());
            sqForm.setUser(agent);
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) != sqForm
                .getApplyLevel()
                    && StringUtils.isNotBlank(sqForm.getToUserId())) {
                Agent toUser = agentBO.getAgent(sqForm.getToUserId());
                sqForm.setToUserName(toUser.getRealName());
            } else if (StringUtils.isNotBlank(sqForm.getToUserId())) {
                SYSUser sysUser = sysUserBO.getSYSUser(sqForm.getToUserId());
                sqForm.setToUserName(sysUser.getRealName());
            }

            // 授权金额
            AgentLevel agentLevel = agentLevelBO
                .getAgentByLevel(sqForm.getApplyLevel());
            sqForm.setImpowerAmount(agentLevel.getMinCharge());
        }
        return page;
    }

    // 分配账号
    private List<String> distributeAccount(String userId, String mobile) {
        List<String> currencyList = new ArrayList<String>();
        currencyList.add(ECurrency.TX_CNY.getCode());
        currencyList.add(ECurrency.MK_CNY.getCode());
        currencyList.add(ECurrency.C_CNY.getCode());
        return currencyList;
    }

    @Override
    public void toQuit(String userId) {
        SqForm sqForm = sqFormBO.getSqForm(userId);
        Agent agent = agentBO.getAgent(userId);
        if (ESqFormStatus.TO_CANCEL.getCode().equals(sqForm.getStatus())
                || ESqFormStatus.CANCEL_COMPANY.getCode()
                    .equals(sqForm.getStatus())) {
            throw new BizException("xn000", "您已申请退出，请勿重复申请");
        }

        agentAO.checkCancel(agent);

        // 最搞级别代理申请，直接由公司审核
        String status = ESqFormStatus.TO_CANCEL.getCode();
        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
            .getLevel()) {
            status = ESqFormStatus.CANCEL_COMPANY.getCode();
        }

        // 获取所有下级门槛款
        Agent condition = new Agent();
        condition.setHighUserId(agent.getUserId());
        List<Agent> list = agentBO.queryAgentList(condition);
        Account mkAccount = null;
        Long amount = 0L;
        for (Agent lowAgent : list) {
            mkAccount = accountBO.getAccountByUser(lowAgent.getUserId(),
                ECurrency.MK_CNY.getCode());
            amount = amount + mkAccount.getAmount();
        }
        // 门槛款不足支付下级上级新上级门槛
        mkAccount = accountBO.getAccountByUser(agent.getUserId(),
            ECurrency.MK_CNY.getCode());
        if (mkAccount.getAmount() < amount) {
            throw new BizException("xn000",
                "您需要充值" + (amount - mkAccount.getAmount()) + "元，转移下级后，才可申请退出");
        }

        sqForm.setToUserId(agent.getHighUserId());
        sqForm.setApplyLevel(agent.getLevel());
        String logCode = sqFormBO.cancelSqForm(sqForm, status);
        agent.setStatus(EAgentStatus.CANCELED.getCode());
        agent.setStatus(status);
        agentBO.refreshLog(agent, logCode);
    }

    private void approveSqForm(SqForm sqForm) {

        // 根据用户类型获取账户列表
        List<String> currencyList = distributeAccount(sqForm.getUserId(),
            sqForm.getRealName());

        List<Account> account = accountBO.getAccountByUser(sqForm.getUserId());
        if (CollectionUtils.isEmpty(account)) {
            // 分配账户
            accountBO.distributeAccount(sqForm.getUserId(),
                sqForm.getApplyLevel(), sqForm.getRealName(),
                EAccountType.Business, currencyList, ESystemCode.BH.getCode(),
                ESystemCode.BH.getCode());
        }

        Address address = addressBO.getDefaultAddress(sqForm.getUserId(),
            EBoolean.YES.getCode());

        if (null == address) {
            // 新增地址
            addressBO.saveAddress(sqForm.getUserId(), sqForm.getMobile(),
                sqForm.getRealName(), sqForm.getProvince(), sqForm.getCity(),
                sqForm.getArea(), sqForm.getAddress(), EBoolean.YES.getCode());
        }

    }

    private void payAward(SqForm sqForm) {
        Long jsAward = 0L;
        if (StringUtils.isNotBlank(sqForm.getIntroducer())) {

            AgentLevel agentLevel = agentLevelBO
                .getAgentByLevel(sqForm.getApplyLevel());
            // 获取介绍奖
            Agent buser = agentBO.getAgent(sqForm.getIntroducer());
            JsAward iData = jsAwardBO.getJsAwardByLevel(buser.getLevel(),
                sqForm.getApplyLevel());
            if (null != iData) {
                jsAward = (long) (agentLevel.getMinCharge()
                        * (iData.getPercent() / 100));

                // 申请等级为最高等级，奖励由公司发，其余由该代理上级发介绍人
                String fromUserId = ESysUser.SYS_USER_BH.getCode();
                if (StringValidater.toInteger(
                    EAgentLevel.ONE.getCode()) != sqForm.getApplyLevel()) {
                    fromUserId = sqForm.getToUserId();
                }

                if (jsAward > 0) {
                    accountBO.transAmountCZB(fromUserId,
                        ECurrency.TX_CNY.getCode(), buser.getUserId(),
                        ECurrency.TX_CNY.getCode(), jsAward, EBizType.AJ_JSJL,
                        "介绍代理[" + sqForm.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getValue() + "支出",
                        "介绍代理[" + sqForm.getRealName() + "]的"
                                + EBizType.AJ_JSJL.getValue() + "收入",
                        sqForm.getUserId());
                }

            }
        }
        Agent agent = agentBO.getAgent(sqForm.getUserId());
        AgentReport report = agentReportBO.getAgentReport(agent.getUserId());
        if (null == report) {
            agentReportBO.saveAgentReport(sqForm, agent, jsAward);
        } else {
            agentReportBO.refreshAgentReport(report, sqForm, agent, jsAward);
        }
    }

}
