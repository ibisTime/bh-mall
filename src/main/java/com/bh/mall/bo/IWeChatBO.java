/**
 * @Title IWechatBO.java 
 * @Package com.std.account.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月27日 下午3:14:48 
 * @version V1.0   
 */
package com.bh.mall.bo;

import java.util.Map;

import com.bh.mall.dto.res.XN627462Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月27日 下午3:14:48 
 * @history:
 */
public interface IWeChatBO {

    public String getPrepayIdH5(Map<String, String> sysConfig, String openId,
            String bizNote, String code, Long transAmount, String ip,
            String bizBackUrl);

    public XN627462Res getPayInfoH5(Map<String, String> sysConfig,
            String payCode, String prepayId);

}
