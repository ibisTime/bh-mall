/**
 * @Title WechatBOImpl.java 
 * @Package com.std.account.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月27日 下午3:16:19 
 * @version V1.0   
 */
package com.bh.mall.bo.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.bh.mall.bo.IWeChatBO;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.PropertiesUtil;
import com.bh.mall.dto.res.XN627462Res;
import com.bh.mall.enums.EWeChatType;
import com.bh.mall.util.wechat.MD5;
import com.bh.mall.util.wechat.MD5Util;
import com.bh.mall.util.wechat.OrderUtil;
import com.bh.mall.util.wechat.WXPrepay;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月27日 下午3:16:19 
 * @history:
 */
@Component
public class WeChatBOImpl implements IWeChatBO {

    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Override
    public String getPrepayIdH5(Map<String, String> sysConfig, String openId,
            String bizNote, String code, Long transAmount, String ip,
            String bizBackUrl) {
        logger.info("code:" + code);
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid(sysConfig.get("private_key2"));// 微信支付分配的公众账号ID
        prePay.setMch_id(sysConfig.get("company_code")); // 商户号
        prePay.setBody(sysConfig.get("name") + "-" + bizNote); // 商品描述
        prePay.setOut_trade_no(code); // 订单号
        prePay.setTotal_fee(Long.toString(transAmount / 10)); // 订单总金额，厘转化成分
        prePay.setSpbill_create_ip(ip); // 用户IP
        prePay.setTrade_type(EWeChatType.JSAPI.getCode()); // 交易类型
        prePay.setNotify_url(bizBackUrl);// 回调地址
        prePay.setPartnerKey(sysConfig.get("private_key1")); // 商户秘钥
        prePay.setOpenid(openId); // 支付者openid
        prePay.setAttach(
            PropertiesUtil.Config.WECHAT_H5_CZ_BACKURL + "||" + bizBackUrl); // 附加字段，回调时返回
        return prePay.submitXmlGetPrepayId();
    }

    @Override
    public XN627462Res getPayInfoH5(Map<String, String> sysConfig,
            String payCode, String prepayId) {
        SortedMap<String, String> nativeObj = new TreeMap<String, String>();
        nativeObj.put("appId", sysConfig.get("private_key2"));
        nativeObj.put("timeStamp", OrderUtil.GetTimestamp());
        Random random = new Random();
        String randomStr = MD5
            .GetMD5String(String.valueOf(random.nextInt(10000)));
        nativeObj.put("nonceStr",
            MD5Util.MD5Encode(randomStr, "utf-8").toLowerCase());
        nativeObj.put("package", "prepay_id=" + prepayId);
        nativeObj.put("signType", "MD5");
        nativeObj.put("paySign",
            createSign(nativeObj, sysConfig.get("private_key1")));

        XN627462Res res = new XN627462Res();
        res.setPrepayId(prepayId);
        res.setPayCode(payCode);
        res.setAppId(nativeObj.get("appId"));
        res.setTimeStamp(nativeObj.get("timeStamp"));
        res.setNonceStr(nativeObj.get("nonceStr"));
        res.setWechatPackage(nativeObj.get("package"));
        res.setSignType(nativeObj.get("signType"));
        res.setPaySign(nativeObj.get("paySign"));
        return res;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    private String createSign(SortedMap<String, String> packageParams,
            String AppKey) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + AppKey);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }
}
