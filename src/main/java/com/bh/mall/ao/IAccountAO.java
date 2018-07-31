package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;

public interface IAccountAO {
    String DEFAULT_ORDER_COLUMN = "account_number";

    /**
     * 个人创建多账户
     * @param userId
     * @param realName
     * @param accountType
     * @param currencyList
     * @param systemCode
     * @param companyCode 
     * @create: 2018年7月31日 下午7:12:24 LENOVO
     * @history:
     */
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode,
            String companyCode);

    /**
     * 更新户名
     * @param userId
     * @param realName 
     * @create: 2018年7月31日 下午7:12:45 LENOVO
     * @history:
     */
    public void editAccountName(String userId, String realName);

    /**
     * 不同用户不同币种间资金划转
     * @param fromUserId
     * @param fromCurrency
     * @param toUserId
     * @param toCurrency
     * @param transAmount
     * @param bizType
     * @param fromBizNote
     * @param toBizNote
     * @param refNo 
     * @create: 2018年7月31日 下午7:12:59 LENOVO
     * @history:
     */
    void transAmountCZB(String fromUserId, String fromCurrency, String toUserId,
            String toCurrency, Long transAmount, String bizType,
            String fromBizNote, String toBizNote, String refNo);

    /**
     * 分页查询账户
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2018年7月31日 下午7:13:14 LENOVO
     * @history:
     */
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    /**
     * 根据accountNumber(账号)查询账户
     * @param accountNumber
     * @return 
     * @create: 2018年7月31日 下午7:13:26 LENOVO
     * @history:
     */
    public Account getAccount(String accountNumber);

    /**
     * 根据用户编号,币种获取账户列表
     * @param userId
     * @param currency
     * @return 
     * @create: 2018年7月31日 下午7:14:11 LENOVO
     * @history:
     */
    public List<Account> getAccountByUserId(String userId, String currency);

    /**
     * 列表查询账户
     * @param condition
     * @return 
     * @create: 2018年7月31日 下午7:14:24 LENOVO
     * @history:
     */
    public List<Account> queryAccountList(Account condition);

    /**
     * 检查红线 
     * @param userId
     * @return 
     * @create: 2018年7月31日 下午7:14:43 LENOVO
     * @history:
     */
    public boolean checkAmount(String userId);

    /**
     * 扣款/充值
     * @param accountNumber
     * @param changeAmount
     * @param remark 
     * @create: 2018年7月31日 下午7:20:33 LENOVO
     * @history:
     */
    public void transAmount(String accountNumber, String changeAmount,
            String remark);

}
