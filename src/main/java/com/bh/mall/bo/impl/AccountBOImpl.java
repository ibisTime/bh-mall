package com.bh.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IJourBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.core.EGeneratePrefix;
import com.bh.mall.core.OrderNoGenerater;
import com.bh.mall.dao.IAccountDAO;
import com.bh.mall.dao.IWareHouseDAO;
import com.bh.mall.domain.Account;
import com.bh.mall.enums.EAccountStatus;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.enums.ESysUser;
import com.bh.mall.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午5:24:53 
 * @history:
 */
@Component
public class AccountBOImpl extends PaginableBOImpl<Account>
        implements IAccountBO {
    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IWareHouseDAO wareHouseDAO;

    @Override
    public String distributeAccount(String userId, String realName,
            EAccountType accountType, List<String> currencyList,
            String systemCode, String companyCode) {
        String accountNumber = null;
        if (StringUtils.isNotBlank(userId)) {
            for (String currency : currencyList) {
                if (ECurrency.YC_CNY.getCode().equals(currency)) {
                    continue;
                }
                accountNumber = OrderNoGenerater
                    .generate(EGeneratePrefix.Account.getCode());
                Account data = new Account();
                data.setAccountNumber(accountNumber);
                data.setUserId(userId);
                data.setRealName(realName);

                data.setType(accountType.getCode());
                data.setCurrency(currency);
                data.setStatus(EAccountStatus.NORMAL.getCode());
                data.setAmount(0L);
                data.setFrozenAmount(0L);

                data.setCreateDatetime(new Date());
                accountDAO.insert(data);
            }
        }
        return accountNumber;
    }

    @Override
    public void changeAmount(String accountNumber, EChannelType channelType,
            String channelOrder, String payGroup, String refNo,
            EBizType bizType, String bizNote, Long transAmount) {
        Account dbAccount = this.getAccount(accountNumber);
        Long dbAmount = dbAccount.getAmount();
        if (null == dbAmount) {
            dbAmount = 0L;
        }
        Long nowAmount = dbAmount + transAmount;
        // 特定账户余额可为负
        if (!dbAccount.getUserId().contains(ESysUser.SYS_USER_BH.getCode())
                && nowAmount < 0) {
            throw new BizException("xn000000", "账户余额不足");
        }
        // 记录流水
        String lastOrder = jourBO.addJour(dbAccount, channelType, channelOrder,
            payGroup, refNo, bizType, bizNote, transAmount);

        // 更改余额
        Account data = new Account();
        data.setAccountNumber(accountNumber);
        data.setAmount(nowAmount);
        data.setLastOrder(lastOrder);
        accountDAO.updateAmount(data);
    }

    @Override
    public void changeAmountNotJour(String accountNumber, Long transAmount,
            String lastOrder) {
        Account dbAccount = this.getAccount(accountNumber);
        Long nowAmount = dbAccount.getAmount() + transAmount;
        if (!dbAccount.getUserId().contains(ESysUser.SYS_USER.getCode())
                && nowAmount < 0) {
            throw new BizException("xn000000", "账户余额不足");
        }
        // 更改余额
        Account data = new Account();
        data.setAccountNumber(accountNumber);
        data.setAmount(nowAmount);
        data.setLastOrder(lastOrder);
        accountDAO.updateAmount(data);
    }

    @Override
    public void frozenAmount(Account dbAccount, Long freezeAmount,
            String withdrawCode) {
        if (freezeAmount <= 0) {
            throw new BizException("xn000000", "冻结金额需大于0");
        }
        Long nowAmount = dbAccount.getAmount() - freezeAmount;
        if (nowAmount < 0) {
            throw new BizException("xn000000", "账户余额不足");
        }
        // 记录流水
        String lastOrder = jourBO.addJour(dbAccount, EChannelType.Offline, null,
            null, withdrawCode, EBizType.AJ_QX, "线下取现", -freezeAmount);
        Long nowFrozenAmount = dbAccount.getFrozenAmount() + freezeAmount;
        Account data = new Account();
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setAmount(nowAmount);
        data.setFrozenAmount(nowFrozenAmount);
        data.setLastOrder(lastOrder);
        accountDAO.frozenAmount(data);
    }

    @Override
    public void unfrozenAmount(Account dbAccount, Long freezeAmount,
            String withdrawCode) {
        if (freezeAmount <= 0) {
            throw new BizException("xn000000", "解冻金额需大于0");
        }
        Long nowFrozenAmount = dbAccount.getFrozenAmount() - freezeAmount;
        if (nowFrozenAmount < 0) {
            throw new BizException("xn000000", "本次解冻会使账户冻结金额小于0");
        }

        // 记录流水
        String lastOrder = jourBO.addJour(dbAccount, EChannelType.Offline, null,
            null, withdrawCode, EBizType.AJ_QX, "线下取现失败退回", freezeAmount);
        Account data = new Account();
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setAmount(dbAccount.getAmount() + freezeAmount);
        data.setFrozenAmount(nowFrozenAmount);
        data.setLastOrder(lastOrder);
        accountDAO.unfrozenAmount(data);
    }

    @Override
    public void cutFrozenAmount(Account dbAccount, Long freezeAmount) {
        if (freezeAmount <= 0) {
            throw new BizException("xn000000", "解冻金额需大于0");
        }
        Long nowFrozenAmount = dbAccount.getFrozenAmount() - freezeAmount;
        if (nowFrozenAmount < 0) {
            throw new BizException("xn000000", "本次扣减会使账户冻结金额小于0");
        }
        Account data = new Account();
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setFrozenAmount(nowFrozenAmount);
        accountDAO.cutFrozenAmount(data);
    }

    @Override
    public void refreshStatus(String accountNumber, EAccountStatus status) {
        if (StringUtils.isNotBlank(accountNumber)) {
            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setStatus(status.getCode());
            accountDAO.updateStatus(data);
        }
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account data = null;
        if (StringUtils.isNotBlank(accountNumber)) {
            Account condition = new Account();
            condition.setAccountNumber(accountNumber);
            data = accountDAO.select(condition);
            if (data == null) {
                throw new BizException("xn702502", "无对应账户，请检查账号正确性");
            }
        }
        return data;
    }

    @Override
    public List<Account> queryAccountList(Account data) {
        return accountDAO.selectList(data);
    }

    /** 
     * @see com.std.account.bo.IAccountBO#getAccountByUser(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Account getAccountByUser(String userId, String currency) {
        Account data = null;
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(currency)) {
            Account condition = new Account();
            condition.setUserId(userId);
            condition.setCurrency(currency);
            data = accountDAO.select(condition);
            if (data == null) {
                throw new BizException("xn802000",
                    "用户[" + userId + ";" + currency + "]无此类型账户");
            }
        }
        return data;
    }

    /** 
     * @see com.std.account.bo.IAccountBO#refreshAccountName(java.lang.String, java.lang.String)
     */
    @Override
    public void refreshAccountName(String userId, String realName) {
        Account data = new Account();
        data.setUserId(userId);
        data.setRealName(realName);
        accountDAO.updateRealName(data);
    }

    /** 
     * @see com.std.account.bo.IAccountBO#getSysAccount(java.lang.String, java.lang.String)
     */
    @Override
    public Account getSysAccountNumber(String systemCode, String companyCode,
            ECurrency currency) {
        Account condition = new Account();
        // 平台账户只有一类,类型+币种+公司+系统=唯一系统账户
        condition.setType(EAccountType.Plat.getCode());
        condition.setCurrency(currency.getCode());
        return accountDAO.select(condition);
    }

    @Override
    public void transAmountCZB(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, Long transAmount,
            EBizType bizType, String fromBizNote, String toBizNote,
            String refNo) {
        Account fromAccount = this.getAccountByUser(fromUserId, fromCurrency);
        Account toAccount = this.getAccountByUser(toUserId, toCurrency);
        transAmountCZB(fromAccount, toAccount, transAmount, bizType,
            fromBizNote, toBizNote, refNo);
    }

    private void transAmountCZB(Account fromAccount, Account toAccount,
            Long transAmount, EBizType bizType, String fromBizNote,
            String toBizNote, String refNo) {
        String fromAccountNumber = fromAccount.getAccountNumber();
        String toAccountNumber = toAccount.getAccountNumber();
        if (fromAccountNumber.equals(toAccountNumber)) {
            new BizException("XN0000", "来去双方账号一致，无需内部划转");
        }
        this.changeAmount(fromAccountNumber, EChannelType.NBZ, null, null,
            refNo, bizType, fromBizNote, -transAmount);
        this.changeAmount(toAccountNumber, EChannelType.NBZ, null, null, refNo,
            bizType, toBizNote, transAmount);
    }
}
