package com.bh.mall.ao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.exception.BizException;

//  TODO  公司账户

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    @Transactional
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode,
            String companyCode) {
        if (CollectionUtils.isNotEmpty(currencyList)) {
            Map<String, EAccountType> map = EAccountType
                .getAccountTypeResultMap();
            EAccountType eAccountType = map.get(accountType);
            if (null == eAccountType) {
                new BizException("XN0000", "账户类型不存在");
            }
        }
    }

    @Override
    public void editAccountName(String userId, String realName) {
        // 验证用户名和系统编号
        Account condition = new Account();
        condition.setUserId(userId);
        List<Account> accountList = accountBO.queryAccountList(condition);
        if (CollectionUtils.isEmpty(accountList)) {
            new BizException("XN0000", "该用户无对应账号");
        }
        accountBO.refreshAccountName(userId, realName);
    }

    @Override
    @Transactional
    public void transAmountCZB(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, Long transAmount,
            String bizType, String fromBizNote, String toBizNote,
            String refNo) {
        EBizType a = EBizType.getBizType(bizType);
        accountBO.transAmountCZB(fromUserId, fromCurrency, toUserId, toCurrency,
            transAmount, a, fromBizNote, toBizNote, refNo);
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {

        Paginable<Account> page = accountBO.getPaginable(start, limit,
            condition);
        List<Account> list = page.getList();
        for (Account account : list) {
            if (!ESysUser.SYS_USER_BH.getCode().equals(account.getUserId())) {
                Agent agent = agentBO.getAgent(account.getUserId());
                account.setAgent(agent);
            }
        }
        page.setList(list);
        return page;
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account data = accountBO.getAccount(accountNumber);
        if (!ESysUser.SYS_USER_BH.getCode().equals(data.getUserId())) {
            Agent Agent = agentBO.getAgent(data.getUserId());
            data.setAgent(Agent);
        }
        return data;
    }

    @Override
    public List<Account> getAccountByUserId(String userId, String currency) {
        Account condition = new Account();
        if (ESysUser.SYS_USER_BH.getCode().equals(userId)) {
            condition.setType(EAccountType.Plat.getCode());
        } else {
            condition.setUserId(userId);
        }

        condition.setCurrency(currency);
        List<Account> list = accountBO.queryAccountList(condition);
        for (Account account : list) {
            Agent Agent = agentBO.getAgent(account.getUserId());
            account.setAgent(Agent);
        }
        return list;
    }

    @Override
    public List<Account> queryAccountList(Account condition) {
        List<Account> list = accountBO.queryAccountList(condition);
        for (Account account : list) {
            if (!ESysUser.SYS_USER_BH.getCode().equals(account.getUserId())) {
                Agent Agent = agentBO.getAgent(account.getUserId());
                account.setAgent(Agent);
            }
        }
        return list;
    }

    @Override
    public boolean checkAmount(String userId) {
        Account account = accountBO.getAccountByUser(userId,
            ECurrency.MK_CNY.getCode());
        Agent user = agentBO.getAgent(userId);
        AgentLevel agent = null;
        if (null != user.getLevel() && 0 != user.getLevel()) {
            agent = agentLevelBO.getAgentByLevel(user.getLevel());
        }
        if (account.getAmount() <= agent.getRedAmount()) {
            return false;
        }
        return true;
    }

    @Override
    public void transAmount(String accountNumber, String changeAmount,
            String remark) {
        Account account = accountBO.getAccount(accountNumber);
        EBizType bizType = EBizType.AJ_KK;
        String bizNote = EBizType.AJ_KK.getValue();
        Long amount = StringValidater.toLong(changeAmount);

        if (amount == 0) {
            throw new BizException("xn00000", "变动金额不为低于零");
        }
        if (amount > 0) {
            bizType = EBizType.AJ_CZ;
            bizNote = EBizType.AJ_CZ.getValue();
        }

        Long totalAmount = amount + account.getAmount();
        if (totalAmount < 0) {
            throw new BizException("xn00000", "用户账户余额不足");
        }
        accountBO.changeAmount(accountNumber, EChannelType.NBZ, null, null,
            account.getUserId(), bizType, bizNote,
            StringValidater.toLong(changeAmount));

    }

}
