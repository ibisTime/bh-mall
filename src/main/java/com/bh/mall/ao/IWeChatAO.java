/**
 * @Title IWeChatAO.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:23:39 
 * @version V1.0   
 */
package com.bh.mall.ao;

import com.bh.mall.dto.res.XN627462Res;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {

    public XN627462Res getPrepayIdH5(String applyUser, String accountNumber,
            String payGroup, String refNo, String bizType, String bizNote,
            Long transAmount, String backUrl);

    public void doCallbackH5(String result);

    public String getAccessToken(String appId, String appSecret);

    // public void doBizCallback(CallbackResult callbackResult);

}
