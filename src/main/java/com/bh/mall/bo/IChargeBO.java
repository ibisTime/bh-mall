package com.bh.mall.bo;

import java.util.Date;
import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.Charge;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;

public interface IChargeBO extends IPaginableBO<Charge> {

    String applyOrderOnline(Account account, String payGroup, String refNo,
            EBizType bizType, String bizNote, Long transAmount,
            EChannelType channelType, Agent agent, Integer level);

    void callBackChange(Charge dbCharge, boolean booleanFlag);

    String applyOrderOffline(Account account, EBizType bizType, Long amount,
            Agent agent, String applyNote, String chargePdf);

    void payOrder(Charge data, String status, String payUser, String payNote);

    List<Charge> queryChargeList(Charge condition);

    Charge getCharge(String code);

    long getFrontTotalCount(Charge condition);

    List<Charge> queryFrontChargePage(int pageNO, int pageSize,
            Charge condition);

    List<Charge> getChargeByUser(String userId, Date impwoeDatetime);

}
