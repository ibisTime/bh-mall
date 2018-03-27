package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IChargeDAO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Charge;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.exception.BizException;

@Component
public class ChargeBOImpl extends PaginableBOImpl<Charge> implements IChargeBO {
    @Autowired
    private IChargeDAO chargeDAO;

    @Override
    public String applyOrderOffline(Account account, Account payAccount,
            EBizType bizType, Long amount, String payCardInfo,
            String payCardNo, String applyUser, String applyNote) {
        if (amount == 0) {
            throw new BizException("xn000000", "充值金额不能为0");
        }
        String code = OrderNoGenerater.generate(EGeneratePrefix.Charge
            .getCode());
        Charge data = new Charge();
        data.setCode(code);
        data.setPayGroup(null);
        data.setRefNo(null);
        data.setAccountNumber(account.getAccountNumber());
        data.setPayAccountNumber(payAccount.getAccountNumber());
        data.setPayAccountName(payAccount.getRealName());
        data.setAmount(amount);

        data.setAccountName(account.getRealName());
        data.setType(account.getType());
        data.setCurrency(account.getCurrency());
        data.setBizType(bizType.getCode());
        if (StringUtils.isBlank(applyNote)) {
            data.setBizNote(bizType.getValue());
        } else {
            data.setBizNote(applyNote);
        }
        data.setPayCardInfo(payCardInfo);
        data.setPayCardNo(payCardNo);

        data.setStatus(EChargeStatus.toPay.getCode());
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setChannelType(EChannelType.Offline.getCode());
        chargeDAO.insert(data);
        return code;
    }

    @Override
    public String applyOrderOnline(Account account, String payGroup,
            String refNo, EBizType bizType, String bizNote, Long transAmount,
            EChannelType channelType, String applyUser) {
        if (transAmount == 0) {
            throw new BizException("xn000000", "充值金额不能为0");
        }
        String code = OrderNoGenerater.generate(EGeneratePrefix.Charge
            .getCode());
        Charge data = new Charge();
        data.setCode(code);
        data.setPayGroup(payGroup);
        data.setRefNo(refNo);
        data.setAccountNumber(account.getAccountNumber());
        data.setAmount(transAmount);

        data.setAccountName(account.getRealName());
        data.setType(account.getType());
        data.setCurrency(account.getCurrency());
        data.setBizType(bizType.getCode());
        data.setBizNote(bizNote);
        data.setPayCardInfo(null);
        data.setPayCardNo(null);

        data.setStatus(EChargeStatus.toPay.getCode());
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setChannelType(channelType.getCode());
        chargeDAO.insert(data);
        return code;
    }

    @Override
    public void payOrder(Charge data, boolean booleanFlag, String payUser,
            String payNote) {
        if (booleanFlag) {
            data.setStatus(EChargeStatus.Pay_YES.getCode());
        } else {
            data.setStatus(EChargeStatus.Pay_NO.getCode());
        }
        data.setPayUser(payUser);
        data.setPayNote(payNote);
        data.setPayDatetime(new Date());
        chargeDAO.payOrder(data);
    }

    @Override
    public void callBackChange(Charge dbCharge, boolean booleanFlag) {
        if (booleanFlag) {
            dbCharge.setStatus(EChargeStatus.Pay_YES.getCode());
        } else {
            dbCharge.setStatus(EChargeStatus.Pay_NO.getCode());
        }
        dbCharge.setPayUser(null);
        dbCharge.setPayNote("在线充值自动回调");
        dbCharge.setPayDatetime(new Date());
        chargeDAO.payOrder(dbCharge);

    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        return chargeDAO.selectList(condition);
    }

    @Override
    public Charge getCharge(String code) {
        Charge order = null;
        if (StringUtils.isNotBlank(code)) {
            Charge condition = new Charge();
            condition.setCode(code);
            order = chargeDAO.select(condition);
            if (null == order) {
                throw new BizException("xn000000", "订单号[" + code + "]不存在");
            }
        }
        return order;
    }
}
