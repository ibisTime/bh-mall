package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IAgentReportBO;
import com.bh.mall.bo.ISYSUserBO;
import com.bh.mall.bo.ISjFormBO;
import com.bh.mall.bo.IWareBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.IdCardChecker;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.AgentReport;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.SjForm;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EAgentStatus;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESjFormStatus;
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
    private IWareBO wareBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAgentReportBO agentReportBO;

    /**
     * 申请升级
     * 1、检验升级门槛是否清零，是否满足半门槛人数（满足的情况下可免费升级）
     * 2、判断是够由不需要实名认证等级升上来，若是进行实名验证
     * 3、判断是否升为最高等级，若是校验团队名称，并更新下级团队名称
     * 4、检查升至申请等级门槛是否需要清零
     */
    @Override
    @Transactional
    public void applySjForm(String userId, String newLevel, String payPdf,
            String payAmount, String teamName, String idKind, String idNo,
            String idHand) {
        Agent data = agentBO.getAgent(userId);

        // 已经申请过升级
        SjForm sjForm = sjFormBO.getSjForm(data.getUserId());
        if (null != sjForm) {
            if (ESjFormStatus.TO_UPGRADE.getCode().equals(sjForm.getStatus())
                    || ESjFormStatus.UPGRADE_COMPANY.getCode()
                        .equals(sjForm.getStatus())) {
                throw new BizException("xn000", "您已经申请过升级了，请勿重复申请");
            }
        }

        if (data.getLevel() <= StringValidater.toInteger(newLevel)) {
            throw new BizException("xn0000", "升级等级要大于当前等级");
        }

        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getLevel()) {
            throw new BizException("xn0000", "您的等级已经为最高等级，无法继续升级");
        }

        // 查看升级所需
        AgentLevel agenthLevel = agentLevelBO
            .getAgentByLevel(StringValidater.toInteger(newLevel));

        // 推荐人数是否满足半门槛
        List<Agent> userReferee = agentBO
            .getAgentByUserReferee(data.getUserId());
        if (agenthLevel.getReNumber() > userReferee.size()) {
            if (StringValidater.toLong(payAmount) < agenthLevel
                .getMinCharge()) {
                throw new BizException("xn00000", "您的直推人数不满足半门槛人数，打款金额不能低于["
                        + agenthLevel.getMinCharge() / 1000.0 + "]元");
            }
        }

        // 余额是否清零
        if (EBoolean.YES.getCode().equals(agenthLevel.getIsReset())) {
            // 门槛是否有余额
            Account account = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.MK_CNY.getCode());
            if (0 < account.getAmount()) {
                throw new BizException("xn00000",
                    "升至[" + agenthLevel.getName() + "]时，门槛中不允许有余额，您的门槛中现剩余["
                            + account.getAmount() / 1000.0 + "]元");
            }
        }

        Account txAccount = accountBO.getAccountByUser(data.getUserId(),
            ECurrency.TX_CNY.getCode());
        if (0 < txAccount.getAmount()) {
            throw new BizException("xn00000", "升级时不允许业绩账户中有余额");
        }

        // 新等级是否需要实名
        if (EBoolean.YES.getCode().equals(agenthLevel.getIsRealName())) {
            // 之前未实名
            if (StringUtils.isBlank(data.getIdNo())
                    || StringUtils.isBlank(data.getIdHand())) {
                if (StringUtils.isBlank(idNo) || StringUtils.isBlank(idHand)) {
                    throw new BizException("xn00000", "升至该等级需完成实名认证");
                }

                // 校验身份证号
                IdCardChecker idCardChecker = new IdCardChecker(idNo);
                if (!idCardChecker.validate()) {
                    throw new BizException("xn00000", "请输入正确的身份证号");
                }
            }

        }

        String status = ESjFormStatus.TO_UPGRADE.getCode();
        String toUserId = data.getHighUserId();

        // 申请等级为董事的代理，直接由平台审核
        if (EAgentLevel.ONE.getCode().equals(newLevel)) {
            status = ESjFormStatus.UPGRADE_COMPANY.getCode();
            // 校验团队名称
            if (StringUtils.isBlank(teamName)) {
                throw new BizException("xn00000", "给自己团队起个名字吧");
            }
            agentBO.checkTeamName(teamName);
            SYSUser sysUser = sysUserBO.getSYSUser();
            toUserId = sysUser.getUserId();
        } else {
            Agent highAgent = agentBO.getAgent(data.getHighUserId());
            if (StringValidater.toInteger(newLevel) <= highAgent.getLevel()) {
                toUserId = this.getHighUser(highAgent,
                    StringValidater.toInteger(newLevel));
            }
        }

        if (StringUtils.isBlank(teamName)) {
            teamName = data.getTeamName();
        }

        // 申请升级
        String logCode = null;
        if (null == sjForm) {
            logCode = sjFormBO.applySjForm(data, toUserId, teamName, newLevel,
                idKind, idNo, idHand, payPdf, payAmount, status);
        } else {
            logCode = sjFormBO.refreshSjForm(sjForm, data, toUserId, teamName,
                newLevel, idKind, idNo, idHand, payPdf, payAmount, status);
        }

        data.setStatus(status);
        agentBO.refreshLog(data, logCode);

    }

    /**
     * 上级审核升级（B端）
     * 1、判断升级是否需要上级审核
     * 2、 检查升至本等级门槛是否清零，若不清零，判断所升等级是否高于等级原上级，高于等于时，检查原上级门槛中余额是否多余该代理门槛余额，不高于时失败。
     * 3、审核通过后，更新云仓价格，清空推荐关系
     * 4、若升级为最高等级，下级同步新的团队名称
     */
    @Override
    @Transactional
    public void approveSjFormByB(String userId, String approver, String result,
            String remark) {
        Agent agent = agentBO.getAgent(userId);
        SjForm sjForm = sjFormBO.getSjForm(userId);
        AgentLevel auData = agentLevelBO
            .getAgentByLevel(sjForm.getApplyLevel());

        // 审核通过
        String status = ESjFormStatus.IMPOWERED.getCode();
        if (EBoolean.YES.getCode().equals(result)) {
            Account account = accountBO.getAccountByUser(sjForm.getUserId(),
                ECurrency.MK_CNY.getCode());

            // 是否需要公司审核
            if (EBoolean.YES.getCode().equals(auData.getIsCompanyApprove())) {
                status = ESjFormStatus.UPGRADE_COMPANY.getCode();
            } else {
                status = ESjFormStatus.THROUGH_YES.getCode();
                // 清空余额
                if (EBoolean.YES.getCode().equals(auData.getIsReset())) {
                    if (account.getAmount() > 0) {
                        accountBO.changeAmount(account.getAccountNumber(),
                            EChannelType.NBZ, null, null, sjForm.getUserId(),
                            EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                            -account.getAmount());
                    }
                }

                if (null != sjForm.getPayAmount()
                        && 0 != sjForm.getPayAmount()) {
                    // 增加账户余额
                    accountBO.changeAmount(account.getAccountNumber(),
                        EChannelType.NBZ, null, null, sjForm.getUserId(),
                        EBizType.AJ_MKCZ, EBizType.AJ_MKCZ.getValue(),
                        sjForm.getPayAmount());
                }

                // 更新云仓价格
                wareBO.changeWarePrice(sjForm.getUserId(),
                    sjForm.getApplyLevel());

                // 清空推荐关系
                agentBO.resetUserReferee(userId);

                // 升级等级为最高等级，下级同步新的团队名称
                if (StringValidater.toInteger(
                    EAgentLevel.ONE.getCode()) == sjForm.getApplyLevel()) {
                    agentAO.editTeamName(userId, sjForm.getTeamName());
                }
                AgentReport report = agentReportBO
                    .getAgentReportByUser(agent.getUserId());
                report.setLevel(sjForm.getApplyLevel());
                report.setTeamName(agent.getTeamName());
                agentReportBO.refreshAward(report);
            }
        }

        Agent approveAgent = agentBO.getAgent(approver);
        String logCode = sjFormBO.approveSjForm(sjForm, agent,
            approveAgent.getUserId(), approveAgent.getRealName(), remark,
            status);
        if (ESjFormStatus.IMPOWERED.getCode().equals(status)) {
            status = EAgentStatus.IMPOWERED.getCode();
        }

        agentBO.refreshSj(agent, sjForm, approveAgent.getUserId(),
            approveAgent.getRealName(), remark, status, logCode);
    }

    /**
     * 上级审核升级（P端）
     * 1、 检查升至本等级门槛是否清零，若不清零，判断所升等级是否高于等级原上级，高于等于时，检查原上级门槛中余额是否多余该代理门槛余额，不高于时失败。
     * 2、审核通过后，更新云仓价格，清空推荐关系
     * 4、若升级为最高等级，下级同步新的团队名称
     */
    @Override
    public void approveSjFormByP(String userId, String approver, String result,
            String remark) {

        SjForm sjForm = sjFormBO.getSjForm(userId);

        Agent agent = agentBO.getAgent(userId);
        // 审核通过
        String status = ESjFormStatus.IMPOWERED.getCode();
        if (EBoolean.YES.getCode().equals(result)) {
            Account account = accountBO.getAccountByUser(sjForm.getUserId(),
                ECurrency.MK_CNY.getCode());
            status = ESjFormStatus.THROUGH_YES.getCode();
            if (null != sjForm.getPayAmount() && 0 != sjForm.getPayAmount()) {
                // 增加账户余额
                accountBO.changeAmount(account.getAccountNumber(),
                    EChannelType.NBZ, null, null, sjForm.getUserId(),
                    EBizType.AJ_QKYE, EBizType.AJ_QKYE.getValue(),
                    sjForm.getPayAmount());
            }
            // 更新云仓价格
            wareBO.changeWarePrice(sjForm.getUserId(), sjForm.getApplyLevel());

            // 清空推荐关系
            agentBO.resetUserReferee(userId);

            // 升级等级为最高等级，下级同步新的团队名称
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == sjForm
                .getApplyLevel()) {
                agentAO.editTeamName(userId, sjForm.getTeamName());
            }

            AgentReport report = agentReportBO
                .getAgentReportByUser(agent.getUserId());
            report.setLevel(sjForm.getApplyLevel());
            agentReportBO.refreshAward(report);
        }

        SYSUser sysUser = sysUserBO.getSYSUser(approver);
        String logCode = sjFormBO.approveSjForm(sjForm, agent,
            sysUser.getUserId(), sysUser.getRealName(), remark, status);

        agentBO.refreshSj(agent, sjForm, sysUser.getUserId(),
            sysUser.getRealName(), remark, status, logCode);
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

        if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == data
            .getApplyLevel()) {
            SYSUser sysUser = sysUserBO.getSYSUser(data.getUserId());
            data.setToUserName(sysUser.getRealName());
        } else {
            Agent toUser = agentBO.getAgent(data.getToUserId());
            data.setToUserName(toUser.getRealName());
        }
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
        for (SjForm sjForm : page.getList()) {
            Agent agent = agentBO.getAgent(sjForm.getUserId());
            sjForm.setUser(agent);
            if (StringValidater.toInteger(EAgentLevel.ONE.getCode()) == sjForm
                .getApplyLevel()) {
                SYSUser sysUser = sysUserBO.getSYSUser(sjForm.getUserId());
                sjForm.setToUserName(sysUser.getRealName());
            } else {
                Agent toUser = agentBO.getAgent(sjForm.getToUserId());
                sjForm.setToUserName(toUser.getRealName());
            }
        }
        return page;
    }

    /*
     * 
     */
    private String getHighUser(Agent highAgent, Integer level) {
        if (level <= highAgent.getLevel()) {
            highAgent = agentBO.getAgent(highAgent.getHighUserId());
            getHighUser(highAgent, level);
        }
        return highAgent.getUserId();
    }

}
