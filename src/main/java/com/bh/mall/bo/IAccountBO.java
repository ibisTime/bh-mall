package com.bh.mall.bo;

import java.util.List;

import com.bh.mall.bo.base.IPaginableBO;
import com.bh.mall.domain.Account;
import com.bh.mall.enums.EAccountStatus;
import com.bh.mall.enums.EAccountType;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.ECurrency;

/**
 * 账户
 * @author: xieyj
 * @since: 2016年11月11日 上午11:23:06 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    // 分配账户
    public String distributeAccount(String userId, String realName,
            EAccountType accountType, List<String> currencyList,
            String systemCode, String companyCode);

    // 变更账户余额：流水落地
    public void changeAmount(String accountNumber, EChannelType channelType,
            String channelOrder, String payGroup, String refNo,
            EBizType bizType, String bizNote, Long transAmount);

    // 变更账户余额
    public void changeAmountNotJour(String accountNumber, Long transAmount,
            String lastOrder);

    // 冻结金额（余额变动）
    public void frozenAmount(Account dbAccount, Long freezeAmount,
            String withdrawCode);

    // 解冻账户(冻结金额原路返回)
    public void unfrozenAmount(Account dbAccount, Long freezeAmount,
            String withdrawCode);

    // 扣减冻结金额
    public void cutFrozenAmount(Account dbAccount, Long amount);

    // 内部转账
    public void transAmountCZB(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, Long transAmount,
            EBizType bizType, String fromBizNote, String toBizNote,
            String refNo);

    // 更新户名
    public void refreshAccountName(String userId, String realName);

    // 更新账户状态
    public void refreshStatus(String accountNumber, EAccountStatus status);

    // 获取账户
    public Account getAccount(String accountNumber);

    // 通过用户编号和币种获取币种
    public Account getAccountByUser(String userId, String currency);

    // 通过用户编号
    public List<Account> getAccountByUser(String userId);

    // 根据系统编号,公司编号和币种获取对应的系统账户(账户类型确定为系统账户)
    public Account getSysAccountNumber(String systemCode, String companyCode,
            ECurrency currency);

    // 获取账户列表
    public List<Account> queryAccountList(Account data);

    // 获取账户
    public Account getAccountNocheck(String userId, String code);

}
