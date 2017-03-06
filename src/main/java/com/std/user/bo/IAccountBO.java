/**
 * @Title IAccountBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:15:49 
 * @version V1.0   
 */
package com.std.user.bo;

import java.util.List;

import com.std.user.enums.EBizType;
import com.std.user.enums.ECurrency;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:24:08 
 * @history:
 */
public interface IAccountBO {

    /**
     * 分配单个账户
     * @param userId
     * @param realName
     * @param currency
     * @param systemCode
     * @return 
     * @create: 2016年12月24日 下午1:26:55 xieyj
     * @history:
     */
    public void distributeAccount(String userId, String realName, String type,
            String currency, String systemCode);

    /**
     * 分配多个账户
     * @param userId
     * @param realName
     * @param currencyList
     * @param systemCode
     * @return 
     * @create: 2016年12月24日 下午1:27:55 xieyj
     * @history:
     */
    public void distributeAccountList(String userId, String realName,
            String type, List<String> currencyList, String systemCode);

    /**
     * 更新户名
     * @param userId
     * @param realName
     * @param systemCode 
     * @create: 2017年1月4日 上午11:46:13 xieyj
     * @history:
     */
    public void refreshRealName(String userId, String realName,
            String systemCode);

    /**
     * 账户之间转账
     * @param fromUserId 从谁
     * @param toUserId 到谁
     * @param amount 支付多少钱
     * @param currency 什么币种
     * @param bizType 因为什么
     * @return 
     * @create: 2017年2月26日 下午2:11:24 myb858
     * @history:
     */
    public void doTransferAmount(String systemCode, String fromUserId,
            String toUserId, Long amount, ECurrency currency, EBizType bizType);
}
