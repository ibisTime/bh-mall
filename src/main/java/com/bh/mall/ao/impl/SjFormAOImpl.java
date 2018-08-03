package com.bh.mall.ao.impl;

import java.util.Date;
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
import com.bh.mall.common.IdCardChecker;
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

    // 升级申请
    @Override
    public void applySjForm(String userId, String newLevel, String payPdf,
            String payAmount, String teamName, String idNo, String inHand) {
        Agent data = agentBO.getAgent(userId);
        if (!(EUserStatus.IMPOWERED.getCode().equals(data.getStatus())
                || EUserStatus.UPGRADED.getCode().equals(data.getStatus()))) {
            throw new BizException("xn000", "您的状态无法申请升级");
        }

        if (data.getLevel() <= StringValidater.toInteger(newLevel)) {
            throw new BizException("xn0000", "升级等级要大于当前等级");
        }

        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            throw new BizException("xn0000", "您的等级已经为最高等级，无法继续升级");
        }

        // 查看升级所需
        AgentLevel highLevel = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(newLevel));

        // 推荐人数是否满足半门槛
        List<Agent> userReferee = agentBO
            .getUsersByUserReferee(data.getUserId());
        if (highLevel.getReNumber() >= userReferee.size()) {
            if (StringValidater.toLong(payAmount) <= highLevel
                .getMinChargeAmount()) {
                throw new BizException("xn00000", "您的直推人数不满足半门槛人数，打款金额不能低于"
                        + StringValidater.toLong(payAmount) / 1000);
            }
        }
        // 余额是否清零
        if (EBoolean.YES.getCode().equals(highLevel.getIsReset())) {
            // 云仓是否有余额
            List<Ware> list = wareBO.getWareByUser(data.getUserId());
            if (CollectionUtils.isNotEmpty(list)) {
                throw new BizException("xn00000", "升至该等级云仓中不允许有余额");
            }
        }

        // 新等级是否需要实名
        if (EBoolean.YES.getCode().equals(highLevel.getIsRealName())) {
            // 之前未实名
            if (StringUtils.isBlank(data.getIdNo())
                    || StringUtils.isBlank(data.getIdHand())) {
                throw new BizException("xn00000", "升至该等级需完成实名认证");
            }

            // 校验身份证号
            IdCardChecker idCardChecker = new IdCardChecker(idNo);
            if (!idCardChecker.validate()) {
                throw new BizException("xn00000", "请输入正确的身份证号");
            }
        }

        String status = EUserStatus.TO_UPGRADE.getCode();
        // 申请等级为董事的代理，直接由平台审核
        if (EAgentLevel.ONE.getCode().equals(highLevel)) {
            EUserStatus.TO_COMPANYCANCEL.getCode();
            // 校验团队名称
            if (StringUtils.isBlank(teamName)) {
                throw new BizException("xn00000", "给自己团队起个名字吧");
            }
            agentBO.checkTeamName(teamName);
        }

        // 新增升级申请记录
        SjForm upData = new SjForm();
        upData.setUserId(userId);
        upData.setApplyLevel(data.getApplyLevel());
        upData.setStatus(status);
        upData.setApplyDatetime(new Date());
        upData.setPayAmount(StringValidater.toLong(payAmount));

        sjFormBO.applySjForm(upData);

    }

    @Override
    @Transactional
    public void approveSjFormByB(String userId, String approver, String result,
            String remark) {

        Agent data = agentBO.getAgent(userId);
        String status = EUserStatus.IMPOWERED.getCode();
        Integer level = data.getApplyLevel();

        // 审核通过
        if (EBoolean.YES.getCode().equals(result)) {
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());

            status = EUserStatus.UPGRADED.getCode();
            AgentLevel auData = agentLevelBO
                .getAgentByLevel(data.getApplyLevel());

            // 是否需要公司审核
            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())) {
                status = EUserStatus.TO_COMPANYUPGRADE.getCode();
            } else {
                level = data.getApplyLevel();
                // 增加账户余额
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, data.getUserId(),
                    EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(), 0L);

            }
        }

        data.setLevel(level);
        data.setStatus(status);
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

    @Override
    public void approveSjFormByP(String userId, String approver, String result,
            String remark) {
        Agent data = agentBO.getAgent(userId);
        String status = EUserStatus.IMPOWERED.getCode();
        Integer level = data.getApplyLevel();

        // 审核通过
        if (EBoolean.YES.getCode().equals(result)) {
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            status = EUserStatus.UPGRADED.getCode();

            level = data.getApplyLevel();
            // 增加账户余额
            accountBO.changeAmount(account.getAccountNumber(), EChannelType.NBZ,
                null, null, data.getUserId(), EBizType.AJ_QKYE,
                EBizType.AJ_QKYE.getValue(), 0L);
        }

        data.setLevel(level);
        data.setStatus(status);
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
            Agent agent = agentAO.getAgent(sjFrom.getUserId());
            sjFrom.setUser(agent);
        }
        return list;
    }

    @Override
    public SjForm getSjForm(String code) {
        SjForm data = sjFormBO.getSjForm(code);
        Agent agent = agentAO.getAgent(data.getUserId());
        data.setUser(agent);
        return data;
    }

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
        for (SjForm uplevelApply : page.getList()) {
            Agent agent = agentAO.getAgent(uplevelApply.getUserId());
            uplevelApply.setUser(agent);
        }
        return page;
    }

}
