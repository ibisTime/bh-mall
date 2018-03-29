package com.bh.mall.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IChargeAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Charge;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;

@Service
public class ChargeAOImpl implements IChargeAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String applyOrder(String accountNumber, Long amount,
            String applyUser, String applyNote, String chargePdf) {
        if (amount <= 0) {
            throw new BizException("xn000000", "充值金额需大于零");
        }
        Account account = accountBO.getAccount(accountNumber);
        // 生成充值订单
        String code = chargeBO.applyOrderOffline(account,
            EBizType.getBizType("AJ_CZ"), amount, applyUser, applyNote,
            chargePdf);
        return code;
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote) {
        Charge data = chargeBO.getCharge(code);
        if (!EChargeStatus.toPay.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote);
        } else {
            payOrderNO(data, payUser, payNote);
        }
    }

    private void payOrderNO(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, false, payUser, payNote);
    }

    private void payOrderYES(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, true, payUser, payNote);
        Account account = accountBO.getAccount(data.getAccountNumber());
        // 账户加钱
        accountBO.changeAmount(data.getAccountNumber(), EChannelType.Offline,
            null, null, data.getCode(), EBizType.AJ_CZ,
            EBizType.AJ_CZ.getValue(), data.getChargeAmount());
        if (ECurrency.YJ_CNY.getCode().equals(account.getCurrency())
                || ECurrency.MK_CNY.getCode().equals(account.getCurrency())) {
            // 托管账户加钱
            accountBO.changeAmount(ESystemCode.BH.getCode(),
                EChannelType.Offline, null, null, data.getCode(),
                EBizType.AJ_CZ, EBizType.AJ_CZ.getValue(),
                data.getChargeAmount());
        }
    }

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {
        Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
        if (CollectionUtils.isNotEmpty(page.getList())) {
            List<Charge> list = page.getList();
            for (Charge charge : list) {
                User user = userBO.getCheckUser(charge.getApplyUser());
                charge.setUser(user);
                if (!"admin".equals(charge.getPayUser())
                        && charge.getPayUser() != null) {
                    User payUser = userBO.getCheckUser(charge.getPayUser());
                    charge.setPayUser(payUser.getLoginName());
                }
            }
        }
        return page;
    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        List<Charge> list = chargeBO.queryChargeList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Charge charge : list) {
                User user = userBO.getCheckUser(charge.getApplyUser());
                charge.setUser(user);
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
        User user = userBO.getCheckUser(charge.getApplyUser());
        charge.setUser(user);
        if (!"admin".equals(charge.getPayUser()) && charge.getPayUser() != null) {
            User payUser = userBO.getCheckUser(charge.getPayUser());
            charge.setPayUser(payUser.getLoginName());
        }
        return charge;
    }

}
