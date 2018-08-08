package com.bh.mall.ao;

import java.util.List;

import com.bh.mall.bo.base.Paginable;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;

/**
 * 账户
 * @author: LENOVO 
 * @since: 2018年7月31日 下午8:34:37 
 * @history:
 */
public interface IAccountAO {

    String DEFAULT_ORDER_COLUMN = "account_number";

    // 个人创建多账户
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode,
            String companyCode);

    // 更新户名
    public void editAccountName(String userId, String realName);

    // 不同用户不同币种间资金划转
    void transAmountCZB(String fromUserId, String fromCurrency, String toUserId,
            String toCurrency, Long transAmount, String bizType,
            String fromBizNote, String toBizNote, String refNo);

    // 分页查询账户
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    // 根据accountNumber(账号)查询账户
    public Account getAccount(String accountNumber);

    // 根据用户编号,币种获取账户列表
    public List<Account> getAccountByUserId(String userId, String currency);

    // 列表查询账户
    public List<Account> queryAccountList(Account condition);

    // 检查红线
    public boolean checkAmount(String userId);

    // 扣款/充值
    public void transAmount(String accountNumber, String changeAmount,
            String remark);

    // 微信H5充值增加上级库存
    public void addHighAccount(Agent agent, Long amount, String channelOrder,
            String payGroup);

}
