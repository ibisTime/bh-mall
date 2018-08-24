package com.bh.mall.bo.impl;

import java.util.ArrayList;
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
import com.bh.mall.domain.Agent;
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
    public String applyOrderOffline(Account account, EBizType bizType,
            Long amount, Agent agent, String applyNote, String chargePdf) {
        if (amount == 0) {
            throw new BizException("xn000000", "充值金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Charge.getCode());
        Charge data = new Charge();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setAccountName(account.getRealName());
        data.setAmount(amount);

        data.setType(account.getType());
        data.setCurrency(account.getCurrency());
        data.setBizType(bizType.getCode());
        if (StringUtils.isBlank(applyNote)) {
            data.setBizNote(bizType.getValue());
        } else {
            data.setBizNote(applyNote);
        }

        data.setStatus(EChargeStatus.TO_Cancel.getCode());
        data.setApplyUser(agent.getUserId());
        data.setHighAgentId(agent.getHighUserId());

        data.setTeamName(agent.getTeamName());
        data.setLevel(agent.getLevel());
        data.setApplyDatetime(new Date());
        data.setChannelType(EChannelType.Offline.getCode());

        data.setChargePdf(chargePdf);
        chargeDAO.insert(data);
        return code;
    }

    @Override
    public String applyOrderOnline(Account account, String payGroup,
            String refNo, EBizType bizType, String bizNote, Long transAmount,
            EChannelType channelType, String applyUser, Integer level) {

        if (transAmount == 0) {
            throw new BizException("xn000000", "充值金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Charge.getCode());
        Charge data = new Charge();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());

        data.setAccountName(account.getRealName());
        data.setPayGroup(payGroup);
        data.setRefNo(refNo);
        data.setAmount(transAmount);

        data.setType(account.getType());
        data.setCurrency(account.getCurrency());
        data.setBizType(bizType.getCode());
        data.setBizNote(bizNote);

        data.setStatus(EChargeStatus.toPay.getCode());
        data.setApplyUser(applyUser);
        data.setLevel(level);
        data.setApplyDatetime(new Date());
        data.setChannelType(channelType.getCode());

        chargeDAO.insert(data);
        return code;
    }

    @Override
    public void payOrder(Charge data, String status, String payUser,
            String payNote) {
        data.setStatus(status);
        data.setPayUser(payUser);
        data.setPayNote(payNote);
        data.setPayDatetime(new Date());
        chargeDAO.payOrder(data);
    }

    @Override
    public void callBackChange(Charge dbCharge, boolean booleanFlag) {
        if (booleanFlag) {

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

    @Override
    public long getFrontTotalCount(Charge condition) {
        return chargeDAO.getFrontTotalCount(condition);
    }

    @Override
    public List<Charge> queryFrontChargePage(int pageNO, int pageSize,
            Charge condition) {
        return chargeDAO.selectFrontChargePage(pageNO, pageSize, condition);
    }

    @Override
    public List<Charge> getChargeByUser(String userId, Date impwoeDatetime) {
        List<String> statusList = new ArrayList<String>();
        statusList.add(EChargeStatus.Pay_YES.getCode());
        statusList.add(EChargeStatus.Cancel_YES.getCode());

        Charge condition = new Charge();
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        condition.setApplyDatetimeStart(impwoeDatetime);

        return chargeDAO.selectList(condition);
    }
}
