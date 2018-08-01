/**
 * @Title IWeChatAO.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:23:39 
 * @version V1.0   
 */
package com.bh.mall.ao;

import java.util.Map;

import com.bh.mall.domain.CallbackResult;
import com.bh.mall.dto.res.XN627462Res;

/** 
 * 微信相关
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {

    // 获取微信支付参数
    public XN627462Res getPrepayIdH5(String applyUser, String accountNumber,
            String payGroup, String refNo, String bizType, String bizNote,
            Long transAmount, String backUrl, String payType);

    // 回调
    public void doCallbackH5(String result);

    // 获取token
    public String getAccessToken(String appId, String appSecret);

    // 回调
    public void doBizCallback(CallbackResult callbackResult);

    // 获取微信充值参数
    public XN627462Res toPrepayIdH5(String applyUser, String accountNumber,
            String payGroup, String refNo, String bizType, String bizNote,
            Long transAmount, String backUrl);

    // 查询订单是否支付成功
    boolean reqOrderquery(Map<String, String> map, String channelType);

}
