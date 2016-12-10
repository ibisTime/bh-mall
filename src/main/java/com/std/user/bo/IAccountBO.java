/**
 * @Title IAccountBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:15:49 
 * @version V1.0   
 */
package com.std.user.bo;

import com.std.user.dto.res.XN802001Res;
import com.std.user.dto.res.XN802013Res;
import com.std.user.dto.res.XN802317Res;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:15:49 
 * @history:
 */
public interface IAccountBO {

    /**
     * 分配账户
     * @param userId
     * @param currency
     * @return 
     * @create: 2015-5-4 下午5:34:16 miyb
     * @history:
     */
    public String distributeAccount(String userId, String realName,
            String currency);

    /**
     * 分配账户二
     * @param userId
     * @param realName
     * @param currency
     * @return 
     * @create: 2016年7月6日 下午8:39:57 xieyj
     * @history:
     */
    public XN802001Res distributeAccountTwo(String userId, String realName,
            String currency, String userReferee);

    public void refreshRealName(String userId, String realName);

    public XN802013Res getAccountDetail(String userId, String currency);

    /**
     * 登录送积分接口
     * @param userId
     * @create: 2016年12月9日 上午9:47:25 xieyj
     * @history:
     */
    public XN802317Res loginAddJf(String userId);
}
