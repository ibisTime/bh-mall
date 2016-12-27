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
     * 更新真实姓名
     * @param userId
     * @param realName 
     * @create: 2016年12月24日 下午1:23:37 xieyj
     * @history:
     */
    public void refreshRealName(String userId, String realName);
}
