package com.bh.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.base.PaginableBOImpl;
import com.bh.mall.dao.IAccountDAO;
import com.bh.mall.domain.Account;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ECurrency;

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

    @Override
    public void distributeAccountList(String userId, String realName,
            String type, List<String> currencyList, String companyCode,
            String systemCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void refreshRealName(String userId, String realName,
            String systemCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote) {
        // TODO Auto-generated method stub

    }

    @Override
    public Account getAccountByUserId(String userId, ECurrency type) {
        Account data = null;
        if (StringUtils.isNotBlank(userId)) {
            Account condition = new Account();
        }
        return null;
    }

    /*
     * @Override public String distributeAccount(String userId, String realName,
     * String type, String currency, String status) { String accountNumber =
     * null; if (StringUtils.isNotBlank(userId)) { // accountNumber =
     * OrderNoGenerater // .generate(EGeneratePrefix.Account.getCode()); //
     * Account data = new Account(); // data.setAccountNumber(accountNumber); //
     * data.setUserId(userId); // data.setRealName(realName); // //
     * data.setType(type); // data.setCurrency(currency); //
     * data.setStatus(status); // data.setAmount(0L); // //
     * data.setMd5(AccountUtil.md5(0L)); // data.setAddAmount(0L); //
     * data.setInAmount(0L); // data.setOutAmount(0L); //
     * data.setCreateDatetime(new Date()); accountDAO.insert(data); } return
     * accountNumber; }
     * @Override public void changeAmount(String accountNumber, String refNo,
     * EJourBizType bizType, String bizNote, Long transAmount) { Account
     * dbAccount = this.getAccount(accountNumber); Long nowAmount =
     * dbAccount.getAmount() + transAmount; // 特定账户余额可为负 if
     * (!dbAccount.getUserId().contains(ESysUser.SYS_USER.getCode()) &&
     * nowAmount < 0) { throw new BizException("xn000000", "账户余额不足"); } // 记录流水
     * String lastOrder = jourBO.addJour(dbAccount, refNo, bizType, bizNote,
     * transAmount); // 更改余额 Account data = new Account();
     * data.setAccountNumber(accountNumber); data.setAmount(nowAmount);
     * data.setMd5(AccountUtil.md5(dbAccount.getMd5(), dbAccount.getAmount(),
     * nowAmount)); // 统计累计增加金额 data.setAddAmount(dbAccount.getAddAmount()); if
     * (transAmount > 0) { data.setAddAmount(dbAccount.getAddAmount() +
     * transAmount); } // 统计累计充值金额 data.setInAmount(dbAccount.getInAmount()); if
     * (EJourBizType.PL_SEND.getCode().equals(bizType.getCode()) ||
     * EJourBizType.JY_CN.getCode().equals(bizType.getCode()) ||
     * EJourBizType.JF_HZ.getCode().equals(bizType.getCode()) ||
     * EJourBizType.AJ_REC_REG.getCode() .equals(bizType.getCode())) {
     * data.setInAmount(dbAccount.getInAmount() + transAmount); }
     * data.setLastOrder(lastOrder); accountDAO.updateAmount(data); }
     * @Override public void changeAmountNotJour(String accountNumber, Long
     * transAmount, String lastOrder) { Account dbAccount =
     * this.getAccount(accountNumber); Long nowAmount = dbAccount.getAmount() +
     * transAmount; if
     * (!dbAccount.getUserId().contains(ESysUser.SYS_USER.getCode()) &&
     * nowAmount < 0) { throw new BizException("xn000000", "账户余额不足"); } // 更改余额
     * Account data = new Account(); data.setAccountNumber(accountNumber);
     * data.setAmount(nowAmount);
     * data.setMd5(AccountUtil.md5(dbAccount.getMd5(), dbAccount.getAmount(),
     * nowAmount)); // 更新统计金额 data.setAddAmount(dbAccount.getAddAmount()); if
     * (transAmount > 0) { data.setAddAmount(dbAccount.getAddAmount() +
     * transAmount); } data.setInAmount(dbAccount.getInAmount());
     * data.setLastOrder(lastOrder); accountDAO.updateAmount(data); }
     * @Override public void refreshStatus(String userId, EUserStatus status) {
     * if (StringUtils.isNotBlank(userId)) { Account data = new Account();
     * data.setAccountNumber(userId); data.setStatus(status.getCode());
     * accountDAO.updateStatus(data); } }
     * @Override public Account getAccount(String accountNumber) { Account data
     * = null; if (StringUtils.isNotBlank(accountNumber)) { Account condition =
     * new Account(); condition.setAccountNumber(accountNumber); data =
     * accountDAO.select(condition); if (data == null) { throw new
     * BizException("xn702502", "无对应账户，请检查账号正确性"); } } return data; }
     * @Override public List<Account> queryAccountList(Account data) { return
     * accountDAO.selectList(data); }
     *//** 
      * @see com.std.account.bo.IAccountBO#getAccountByUser(java.lang.String, java.lang.String, java.lang.String)
      */
    /*
     * @Override public Account getAccountByUser(String userId, String currency)
     * { Account data = null; if (StringUtils.isNotBlank(userId) &&
     * StringUtils.isNotBlank(currency)) { Account condition = new Account();
     * condition.setUserId(userId); condition.setCurrency(currency); data =
     * accountDAO.select(condition); if (data == null) { throw new
     * BizException("xn802000", "用户[" + userId + ";" + currency + "]无此类型账户"); }
     * } return data; }
     *//** 
      * @see com.std.account.bo.IAccountBO#refreshAccountName(java.lang.String, java.lang.String)
      */
    /*
     * @Override public void refreshAccountName(String userId, String realName)
     * { Account data = new Account(); data.setUserId(userId);
     * data.setRealName(realName); accountDAO.updateRealName(data); }
     *//** 
      * @see com.std.account.bo.IAccountBO#getSysAccount(java.lang.String, java.lang.String)
      *//*
         * @Override public Account getSysAccountNumber(ECurrency currency) {
         * Account condition = new Account(); // 平台账户只有一类,类型+币种+公司+系统=唯一系统账户
         * condition.setType(EAccountType.Plat.getCode());
         * condition.setCurrency(currency.getCode()); return
         * accountDAO.select(condition); }
         * @Override public void transAmountCZB(String fromUserId, String
         * fromCurrency, String toUserId, String toCurrency, Long transAmount,
         * EJourBizType bizType, String fromBizNote, String toBizNote, String
         * refNo) { Account fromAccount = this.getAccountByUser(fromUserId,
         * fromCurrency); Account toAccount = this.getAccountByUser(toUserId,
         * toCurrency); transAmountCZB(fromAccount, toAccount, transAmount,
         * bizType, fromBizNote, toBizNote, refNo); } private void
         * transAmountCZB(Account fromAccount, Account toAccount, Long
         * transAmount, EJourBizType bizType, String fromBizNote, String
         * toBizNote, String refNo) { String fromAccountNumber =
         * fromAccount.getAccountNumber(); String toAccountNumber =
         * toAccount.getAccountNumber(); if
         * (fromAccountNumber.equals(toAccountNumber)) { new
         * BizException("XN0000", "来去双方账号一致，无需内部划转"); }
         * this.changeAmount(fromAccountNumber, refNo, bizType, fromBizNote,
         * -transAmount); this.changeAmount(toAccountNumber, refNo, bizType,
         * toBizNote, transAmount); }
         * @Override public Long getTotalAmount(String type) { Account condition
         * = new Account(); condition.setType(type); return
         * accountDAO.selectTotalAmount(condition); }
         * @Override public void distributeAccountList(String userId, String
         * realName, String type, List<String> currencyList, String companyCode,
         * String systemCode) { // TODO Auto-generated method stub }
         * @Override public void refreshRealName(String userId, String realName,
         * String systemCode) { // TODO Auto-generated method stub }
         * @Override public void doTransferAmountRemote(String fromUserId,
         * String toUserId, ECurrency currency, Long amount, EBizType bizType,
         * String fromBizNote, String toBizNote) { // TODO Auto-generated method
         * stub }
         */
}
