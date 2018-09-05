package com.bh.mall.ao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.ao.IWithdrawAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IWithdrawBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.AmountUtil;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.SYSUser;
import com.bh.mall.domain.Withdraw;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EAgentLevel;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.enums.EWithdrawStatus;
import com.bh.mall.exception.BizException;

@Service
public class WithdrawAOImpl implements IWithdrawAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IAgentBO agentBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private ISYSUserAO sysUserAO;

    @Override
    @Transactional
    public String applyOrderTradePwd(String accountNumber, Long amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote, String tradePwd) {
        if (amount <= 0) {
            throw new BizException("xn000000", "提现金额需大于零");
        }

        Account dbAccount = accountBO.getAccount(accountNumber);
        // 判断本月是否次数已满，且现在只能有一笔取现未支付记录
        withdrawBO.doCheckTimes(dbAccount);
        // 验证交易密码
        agentBO.checkTradePwd(dbAccount.getUserId(), tradePwd);
        if (dbAccount.getAmount() < amount) {
            throw new BizException("xn000000", "余额不足");
        }

        String isCompanyPay = EBoolean.NO.getCode();
        if (ECurrency.C_CNY.getCode().equals(dbAccount.getCurrency())) {
            isCompanyPay = EBoolean.YES.getCode();
        }

        // 生成取现订单
        Long fee = doGetFee(dbAccount.getType(), amount,
            ESystemCode.BH.getCode(), ESystemCode.BH.getCode());
        // 取现总金额
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount, fee,
            payCardInfo, payCardNo, applyUser, applyNote, isCompanyPay);
        // 冻结取现金额
        amount = amount + fee;
        accountBO.frozenAmount(dbAccount, amount, withdrawCode, applyNote);
        return withdrawCode;
    }

    @Override
    @Transactional
    public String applyOrder(String accountNumber, Long amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {
        if (amount <= 0) {
            throw new BizException("xn000000", "提现金额需大于零");
        }
        Account dbAccount = accountBO.getAccount(accountNumber);
        // 判断本月是否次数已满，且现在只能有一笔取现未支付记录
        withdrawBO.doCheckTimes(dbAccount);
        if (dbAccount.getAmount() < amount) {
            throw new BizException("xn000000", "余额不足");
        }

        String isCompanyPay = EBoolean.NO.getCode();
        Agent agent = agentBO.getAgent(applyUser);
        if (ECurrency.C_CNY.getCode().equals(dbAccount.getCurrency())
                || StringValidater.toInteger(EAgentLevel.ONE.getCode()) == agent
                    .getLevel()) {
            isCompanyPay = EBoolean.YES.getCode();
        }

        // 手续费
        Long fee = doGetFee(dbAccount.getCurrency(), amount,
            ESystemCode.BH.getCode(), ESystemCode.BH.getCode());

        // 取现总金额
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount - fee,
            fee, payCardInfo, payCardNo, applyUser, applyNote, isCompanyPay);

        // 冻结取现金额
        accountBO.frozenAmount(dbAccount, amount, withdrawCode, applyNote);
        return withdrawCode;
    }

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.toApprove.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待审批状态，无法审批");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            approveOrderYES(data, approveUser, approveNote);
        } else {
            approveOrderNO(data, approveUser, approveNote);
        }
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder);
        } else {
            payOrderNO(data, payUser, payNote, channelOrder);
        }
    }

    private void approveOrderYES(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES, approveUser,
            approveNote);
    }

    private void approveOrderNO(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_NO, approveUser,
            approveNote);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, dbAccount.getFrozenAmount(),
            data.getCode(), approveNote);
    }

    private void payOrderNO(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_NO, payUser, payNote,
            payCode);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(), data.getCode(),
            payNote);
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
            payCode);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 扣减冻结流水
        accountBO.cutFrozenAmount(dbAccount, data.getAmount());
        Account account = accountBO.getAccount(data.getAccountNumber());
        if (ECurrency.TX_CNY.getCode().equals(account.getCurrency())
                || ECurrency.YC_CNY.getCode().equals(account.getCurrency())) {
            // 托管账户减钱
            accountBO.changeAmount(ESysUser.TG_BH.getCode(),
                EChannelType.Offline, null, null, data.getCode(),
                EBizType.AJ_QX, "线下取现", -data.getAmount());
        }
    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        Paginable<Withdraw> page = withdrawBO.getPaginable(start, limit,
            condition);
        if (CollectionUtils.isNotEmpty(page.getList())) {
            SYSUser sysUser = null;
            for (Withdraw data : page.getList()) {
                if (EAccountType.Business.getCode().equals(data.getType())) {
                    Agent agent = agentBO.getAgent(data.getApplyUser());
                    data.setAgent(agent);
                }

                if (StringUtils.isNotBlank(data.getApproveUser())) {
                    if (EBoolean.YES.getCode().equals(data.getIsCompanyPay())) {
                        sysUser = sysUserAO.getSYSUser(data.getApproveUser());
                        data.setApproveName(sysUser.getRealName());
                    } else {
                        Agent agent = agentBO.getAgent(data.getApproveUser());
                        data.setApproveName(agent.getRealName());
                    }

                }
                if (StringUtils.isNotBlank(data.getPayUser())) {
                    if (EBoolean.YES.getCode().equals(data.getIsCompanyPay())) {
                        sysUser = sysUserAO.getSYSUser(data.getPayUser());
                        data.setApproveName(sysUser.getRealName());
                    } else {
                        Agent agent = agentBO.getAgent(data.getPayUser());
                        data.setApproveName(agent.getRealName());
                    }

                }
            }
        }
        return page;
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        List<Withdraw> list = withdrawBO.queryWithdrawList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            SYSUser sysUser = null;
            for (Withdraw data : list) {
                Agent agent = agentBO.getAgent(data.getApplyUser());
                data.setAgent(agent);
                if (StringUtils.isNotBlank(data.getApproveUser())) {
                    if (EBoolean.YES.getCode().equals(data.getIsCompanyPay())) {
                        sysUser = sysUserAO.getSYSUser(data.getApproveUser());
                        data.setApproveName(sysUser.getRealName());
                    } else {
                        agent = agentBO.getAgent(data.getApproveUser());
                        data.setApproveName(agent.getRealName());
                    }

                }
                if (StringUtils.isNotBlank(data.getPayUser())) {
                    if (EBoolean.YES.getCode().equals(data.getIsCompanyPay())) {
                        sysUser = sysUserAO.getSYSUser(data.getPayUser());
                        data.setApproveName(sysUser.getRealName());
                    } else {
                        agent = agentBO.getAgent(data.getPayUser());
                        data.setApproveName(agent.getRealName());
                    }

                }
            }
        }
        return list;
    }

    @Override
    public Withdraw getWithdraw(String code) {
        Withdraw data = withdrawBO.getWithdraw(code);
        Agent agent = agentBO.getAgent(data.getApplyUser());
        data.setAgent(agent);

        if (StringUtils.isNotBlank(data.getApproveUser())) {
            if (EBoolean.YES.getCode().equals(data.getIsCompanyPay())) {
                SYSUser sysUser = sysUserAO.getSYSUser(data.getApproveUser());
                data.setApproveName(sysUser.getRealName());
            } else {
                agent = agentBO.getAgent(data.getApproveUser());
                data.setApproveName(agent.getRealName());
            }

        }
        if (StringUtils.isNotBlank(data.getPayUser())) {
            if (EBoolean.YES.getCode().equals(data.getIsCompanyPay())) {
                SYSUser sysUser = sysUserAO.getSYSUser(data.getPayUser());
                data.setApproveName(sysUser.getRealName());
            } else {
                agent = agentBO.getAgent(data.getPayUser());
                data.setApproveName(agent.getRealName());
            }

        }
        return data;
    }

    /**
     * 取现申请检查，验证参数，返回手续费
     * @param accountType
     * @param amount
     * @param systemCode
     * @param companyCode
     * @return 
     * @create: 2017年5月17日 上午7:53:01 xieyj
     * @history:
     */
    private Long doGetFee(String accountType, Long amount, String systemCode,
            String companyCode) {
        Map<String, String> argsMap = sysConfigBO.getConfigsMap(null,
            systemCode, companyCode);
        String qxbs = null;
        String qxfl = null;
        if (ECurrency.C_CNY.getCode().equals(accountType)) {
            qxfl = SysConstant.BUSERWDQXFL;
            qxbs = SysConstant.BUSERQXBS;

            String minAmount = argsMap.get(SysConstant.QXDBZDJE);
            if (StringUtils.isNotBlank(qxfl)) {
                Long qxDbzdje = AmountUtil.mul(1000L, Double.valueOf(qxfl));
                if (amount > qxDbzdje) {
                    throw new BizException("xn000000",
                        "取现单笔最低金额不能低于" + minAmount + "元。");
                }
            }
        } else if (ECurrency.TX_CNY.getCode().equals(accountType)) {
            qxbs = SysConstant.BUSERQXBS;
            qxfl = SysConstant.BUSERQXFL;
        } else {// 暂定其他账户类型不收手续费
            return 0L;
        }
        // 取现单笔最大金额
        String qxDbzdjeValue = argsMap.get(SysConstant.QXDBZDJE);
        if (StringUtils.isNotBlank(qxDbzdjeValue)) {
            Long qxDbzdje = AmountUtil.mul(1000L,
                Double.valueOf(qxDbzdjeValue));
            if (amount > qxDbzdje) {
                throw new BizException("xn000000",
                    "取现单笔最大金额不能超过" + qxDbzdjeValue + "元。");
            }
        }

        String qxBsValue = argsMap.get(qxbs);
        if (StringUtils.isNotBlank(qxBsValue)) {
            // 取现金额倍数
            Long qxBs = AmountUtil.mul(1000L, Double.valueOf(qxBsValue));
            if (qxBs > 0 && amount % qxBs > 0) {
                throw new BizException("xn000000", "金额请取" + qxBsValue + "的倍数");
            }
        }
        String feeRateValue = argsMap.get(qxfl);
        Double feeRate = 0D;
        if (StringUtils.isNotBlank(feeRateValue)) {
            feeRate = Double.valueOf(feeRateValue);
        }
        return AmountUtil.mul(amount, feeRate);
    }

    @Override
    public String backRecord(String accountNumber, Long amount,
            String payDatetime, String payCardInfo, String payCardNo,
            String applyUser, String applyNote) {
        Account dbAccount = accountBO.getAccount(accountNumber);
        String code = withdrawBO.backRecord(dbAccount, amount,
            DateUtil.strToDate(payDatetime, DateUtil.FRONT_DATE_FORMAT_STRING),
            payCardInfo, payCardNo, applyUser, applyNote);
        accountBO.changeAmount(accountNumber, EChannelType.Offline, null, null,
            code, EBizType.XXFK, "提现回录", -amount);
        return code;
    }

}
