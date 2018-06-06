package com.bh.mall.ao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IUserBO;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.User;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.exception.BizException;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

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
            // for (String currency : currencyList) {
            // accountBO.distributeAccount(userId, realName, eAccountType,
            // currency, systemCode, companyCode);
            // }
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
                User user = userBO.getUser(account.getUserId());
                account.setUser(user);
            }
        }
        page.setList(list);
        return page;
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account data = accountBO.getAccount(accountNumber);
        if (!ESysUser.SYS_USER_BH.getCode().equals(data.getUserId())) {
            User user = userBO.getUser(data.getUserId());
            data.setUser(user);
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
            User user = userBO.getUser(account.getUserId());
            account.setUser(user);
        }
        return list;
    }

    @Override
    public List<Account> queryAccountList(Account condition) {
        List<Account> list = accountBO.queryAccountList(condition);
        for (Account account : list) {
            if (!ESysUser.SYS_USER_BH.getCode().equals(account.getUserId())) {
                User user = userBO.getUser(account.getUserId());
                account.setUser(user);
            }
        }
        return list;
    }

}
