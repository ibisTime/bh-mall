package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IChargeAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentImpowerBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentImpower;
import com.bh.mall.domain.Charge;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.enums.EUser;
import com.bh.mall.exception.BizException;

@Service
public class ChargeAOImpl implements IChargeAO {

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IChargeBO chargeBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAgentImpowerBO agentImpowerBO;

    @Override
    public String applyOrder(String accountNumber, String type, Long amount,
            String applyUser, String applyNote, String chargePdf) {
        if (amount < 0) {
            throw new BizException("xn000000", "金额需大于零");
        }
        User user = userBO.getUser(applyUser);
        // 没有充值过
        List<Charge> charge = chargeBO.getChargeByUser(user.getUserId());
        if (CollectionUtils.isEmpty(charge)) {
            // 首次不能低于授权金额
            AgentImpower impower = agentImpowerBO
                .getAgentImpowerByLevel(user.getLevel());
            if (impower.getMinCharge() > amount) {
                throw new BizException("xn000000",
                    "授权金额不能低于[" + impower.getMinCharge() / 1000 + "]");
            }

        }
        // 普通充值
        Agent agent = agentBO.getAgentByLevel(user.getLevel());
        if (agent.getMinChargeAmount() > amount) {
            throw new BizException("xn000000",
                "充值金额不能低于[" + agent.getMinChargeAmount() / 1000 + "]");
        }

        Account account = accountBO.getAccount(accountNumber);
        Long tranAmount = amount;
        if (EBizType.AJ_KK.getCode().equals(type)) {
            tranAmount = -amount;
        }
        // 生成充值订单
        String code = chargeBO.applyOrderOffline(account,
            EBizType.getBizType(type), tranAmount, applyUser, applyNote,
            chargePdf);
        return code;
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote) {
        Charge data = chargeBO.getCharge(code);
        if (!EChargeStatus.TO_Cancel.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该充值单不是待审核状态");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote);
        } else {
            payOrderNO(data, payUser, payNote);
        }
    }

    private void payOrderNO(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, EChargeStatus.Cancel_NO.getCode(), payUser,
            payNote);
    }

    private void payOrderYES(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, EChargeStatus.Cancel_YES.getCode(), payUser,
            payNote);
        // Account account = accountBO.getAccount(data.getAccountNumber());
        // 账户加钱
        accountBO.changeAmount(data.getAccountNumber(), EChannelType.Offline,
            null, null, data.getCode(), EBizType.getBizType(data.getBizType()),
            EBizType.getBizType(data.getBizType()).getValue(),
            data.getAmount());
        // if (ECurrency.YJ_CNY.getCode().equals(account.getCurrency())) {
        // // 托管账户加钱
        // accountBO.changeAmount(ESysUser.SYS_USER_BH.getCode(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EBizType.getBizType(data.getBizType()),
        // EBizType.getBizType(data.getBizType()).getValue(),
        // data.getAmount());
        // }
    }

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {

        Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
        for (Charge charge : page.getList()) {
            // 获取一级代理
            if (!EUser.ADMIN.getCode().equals(charge.getPayUser())
                    && charge.getPayUser() != null) {
                User payUser = userBO.getCheckUser(charge.getPayUser());
                charge.setPayUser(payUser.getLoginName());
            }
        }
        return page;
    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        List<Charge> list = chargeBO.queryChargeList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Charge charge : list) {
                if (!"admin".equals(charge.getApplyUser())
                        && charge.getApplyUser() != null) {
                    User user = userBO.getCheckUser(charge.getApplyUser());
                    charge.setUser(user);
                }
                if (!"admin".equals(charge.getPayUser())
                        && charge.getPayUser() != null) {
                    User payUser = userBO.getCheckUser(charge.getPayUser());
                    charge.setPayUser(payUser.getLoginName());
                }
            }
        }
        return list;
    }

    @Override
    public Charge getCharge(String code) {
        Charge charge = chargeBO.getCharge(code);
        if (!"admin".equals(charge.getApplyUser())
                && charge.getApplyUser() != null) {
            User user = userBO.getCheckUser(charge.getApplyUser());
            charge.setUser(user);
        }
        if (!"admin".equals(charge.getPayUser())
                && charge.getPayUser() != null) {
            User payUser = userBO.getCheckUser(charge.getPayUser());
            charge.setPayUser(payUser.getLoginName());
        }
        return charge;
    }

    @Override
    public Paginable<Charge> queryFrontChargePage(int start, int limit,
            Charge condition) {

        long count = chargeBO.getFrontTotalCount(condition);
        Page<Charge> page = new Page<Charge>(start, limit, count);
        List<Charge> list = chargeBO.queryFrontChargePage(page.getStart(),
            page.getPageSize(), condition);

        for (Charge charge : list) {
            if (!EUser.ADMIN.getCode().equals(charge.getApplyUser())
                    && charge.getApplyUser() != null) {
                User user = userBO.getCheckUser(charge.getApplyUser());
                charge.setUser(user);
            }
            if (!EUser.ADMIN.getCode().equals(charge.getPayUser())
                    && charge.getPayUser() != null) {
                User payUser = userBO.getCheckUser(charge.getPayUser());
                charge.setPayUser(payUser.getLoginName());
            }
        }
        page.setList(list);
        return page;
    }

}
