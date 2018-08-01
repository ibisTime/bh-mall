package com.bh.mall.ao.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.mall.ao.IWeChatAO;
import com.bh.mall.bo.IAccountBO;
import com.bh.mall.bo.IAgentBO;
import com.bh.mall.bo.IAgentLevelBO;
import com.bh.mall.bo.IChargeBO;
import com.bh.mall.bo.IJourBO;
import com.bh.mall.bo.ISYSConfigBO;
import com.bh.mall.bo.IWeChatBO;
import com.bh.mall.callback.CallbackBzdhConroller;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.common.SysConstant;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Agent;
import com.bh.mall.domain.AgentLevel;
import com.bh.mall.domain.CallbackResult;
import com.bh.mall.domain.Charge;
import com.bh.mall.dto.res.XN627462Res;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.EChannelType;
import com.bh.mall.enums.EChargeStatus;
import com.bh.mall.enums.EPayType;
import com.bh.mall.enums.ESysConfigType;
import com.bh.mall.enums.ESystemCode;
import com.bh.mall.exception.BizException;
import com.bh.mall.http.PostSimulater;
import com.bh.mall.util.HttpsUtil;
import com.bh.mall.util.wechat.TokenResponse;
import com.bh.mall.util.wechat.WXOrderQuery;
import com.bh.mall.util.wechat.XMLUtil;

@Service
public class WeChatAOImpl implements IWeChatAO {

    private static Logger logger = Logger
        .getLogger(CallbackBzdhConroller.class);

    @Autowired
    IWeChatBO weChatBO;

    @Autowired
    IJourBO jourBO;

    @Autowired
    IChargeBO chargeBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IAgentBO agentBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IAgentLevelBO agentLevelBO;

    @Override
    @Transactional
    public XN627462Res toPrepayIdH5(String applyUser, String accountNumber,
            String payGroup, String refNo, String bizType, String bizNote,
            Long transAmount, String backUrl) {
        Agent user = agentBO.getAgent(applyUser);
        AgentLevel aData = agentLevelBO.getAgentByLevel(user.getLevel());
        if (null != aData.getMinChargeAmount()
                && (aData.getMinChargeAmount() / 1000) > transAmount) {
            throw new BizException("xn000000",
                "充值金额不能低于[" + aData.getMinChargeAmount() + "]");
        }
        // 获取收款方账户信息
        Account toAccount = accountBO.getAccount(accountNumber);
        // 落地此次付款的订单信息
        String chargeOrderCode = chargeBO.applyOrderOnline(toAccount, payGroup,
            refNo, EBizType.getBizType(bizType), bizNote, transAmount,
            EChannelType.WeChat_H5, applyUser);
        return this.getPrepayIdH5(applyUser, accountNumber, payGroup,
            chargeOrderCode, bizType, bizNote, transAmount, backUrl,
            EChannelType.WeChat_H5.getCode());
    }

    @Override
    @Transactional
    public XN627462Res getPrepayIdH5(String applyUser, String accountNumber,
            String payGroup, String refNo, String bizType, String bizNote,
            Long transAmount, String backUrl, String payType) {
        Agent agent = agentBO.getAgent(applyUser);

        if (transAmount.longValue() == 0l) {
            throw new BizException("xn000000", "发生金额为零，不能使用微信支付");
        }
        String openId = agent.getH5OpenId();
        if (StringUtils.isBlank(openId)) {
            throw new BizException("xn0000", "请先微信登录再支付");
        }
        Map<String, String> sysConfig = sysConfigBO.getConfigsMap(payType);
        // 获取微信公众号支付prepayid
        String prepayId = weChatBO.getPrepayIdH5(sysConfig, openId, bizNote,
            refNo, transAmount, SysConstant.IP, backUrl);
        // 返回微信APP支付所需信息
        return weChatBO.getPayInfoH5(sysConfig, refNo, prepayId);
    }

    @Override
    @Transactional
    public void doCallbackH5(String result) {
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);
            String attach = map.get("attach");
            String[] codes = attach.split("\\|\\|");
            String systemCode = codes[0];
            String companyCode = codes[1];
            String bizBackUrl = codes[2];
            String wechatOrderNo = map.get("transaction_id");
            String outTradeNo = map.get("out_trade_no");

            // 取到订单信息
            Charge order = chargeBO.getCharge(outTradeNo);
            if (!EChargeStatus.toPay.getCode().equals(order.getStatus())) {
                throw new BizException("xn000000", "充值订单不处于待支付状态，重复回调");
            }
            // 此处调用订单查询接口验证是否交易成功
            boolean isSucc = reqOrderquery(map,
                EChannelType.WeChat_H5.getCode());
            // 支付成功，商户处理后同步返回给微信参数
            if (isSucc) {
                // 更新充值订单状态
                chargeBO.callBackChange(order, true);
                // 收款方账户加钱
                accountBO.changeAmount(order.getAccountNumber(),
                    EChannelType.getEChannelType(order.getChannelType()),
                    wechatOrderNo, order.getPayGroup(), order.getRefNo(),
                    EBizType.getBizTypeMap().get(order.getBizType()),
                    order.getBizNote(), order.getAmount());
                // 托管账户加钱
                accountBO.changeAmount(ESystemCode.BH.getCode(),
                    EChannelType.getEChannelType(order.getChannelType()),
                    wechatOrderNo, order.getPayGroup(), order.getRefNo(),
                    EBizType.getBizTypeMap().get(order.getBizType()),
                    order.getBizNote(), order.getAmount());

                // 上级加钱 TODO
                // Agent agent = agentBO.getAgent(order.getApplyUser());
                // userAO.addHighAccount(agent, order.getAmount());
            } else {
                // 更新充值订单状态
                chargeBO.callBackChange(order, false);
            }
            CallbackResult callbackResult = new CallbackResult(isSucc,
                order.getBizType(), order.getCode(), order.getPayGroup(),
                order.getAmount(), systemCode, companyCode, bizBackUrl);
            // 回调业务biz
            doBizCallback(callbackResult);
        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }
    }

    @Override
    public String getAccessToken(String appId, String appSecret) {
        String accessToken = null;
        String postUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appId + "&secret=" + appSecret;
        String response = null;
        try {
            response = new String(HttpsUtil.post(postUrl, "", "UTF-8"));
            TokenResponse tokenResponse = JsonUtil.json2Bean(response,
                TokenResponse.class);
            accessToken = tokenResponse.getAccess_token();
        } catch (Exception e) {
            throw new BizException("xn000000", "获取微信accessToekn失败，请检查参数");
        }
        return accessToken;
    }

    @Override
    public boolean reqOrderquery(Map<String, String> map, String channelType) {
        System.out.println("******* 开始订单查询 ******");
        WXOrderQuery orderQuery = new WXOrderQuery();
        orderQuery.setAppid(map.get("appid"));
        orderQuery.setMch_id(map.get("mch_id"));
        orderQuery.setTransaction_id(map.get("transaction_id"));
        orderQuery.setOut_trade_no(map.get("out_trade_no"));
        orderQuery.setNonce_str(map.get("nonce_str"));

        logger.info("out_trade_no：" + map.get("out_trade_no"));

        String attach = map.get("attach");
        String[] codes = attach.split("\\|\\|");

        Map<String, String> sysConfig = null;

        if (EPayType.WEIXIN_H5.getCode().equals(channelType)) {
            sysConfig = sysConfigBO
                .getConfigsMap(ESysConfigType.WX_PAY.getCode());

        } else {
            sysConfig = sysConfigBO
                .getConfigsMap(ESysConfigType.XCX_PAY.getCode());
        }

        if (null == sysConfig) {
            throw new BizException("xn00000", "获取支付渠道异常");
        }
        String privateKey1 = sysConfig.get("private_key1");

        // 此处需要密钥PartnerKey，此处直接写死，自己的业务需要从持久化中获取此密钥，否则会报签名错误
        orderQuery.setPartnerKey(privateKey1);

        Map<String, String> orderMap = orderQuery.reqOrderquery();
        // 此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
        if (orderMap.get("return_code") != null
                && orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
            if (orderMap.get("trade_state") != null && orderMap
                .get("trade_state").equalsIgnoreCase("SUCCESS")) {
                String total_fee = map.get("total_fee");
                String order_total_fee = map.get("total_fee");
                if (Integer.parseInt(order_total_fee) >= Integer
                    .parseInt(total_fee)) {
                    System.out.println("******* 开订单查询结束，结果正确 ******");
                    return true;
                }
            }
        }
        System.out.println("******* 开订单查询结束，结果不正确 ******");
        return false;
    }

    @Override
    public void doBizCallback(CallbackResult callbackResult) {
        try {
            Properties formProperties = new Properties();
            formProperties.put("isSuccess", callbackResult.isSuccess());
            formProperties.put("systemCode", callbackResult.getSystemCode());
            formProperties.put("companyCode", callbackResult.getCompanyCode());
            formProperties.put("payGroup", callbackResult.getPayGroup());
            formProperties.put("payCode", callbackResult.getJourCode());
            formProperties.put("bizType", callbackResult.getBizType());
            formProperties.put("transAmount", callbackResult.getTransAmount());
            PostSimulater.requestPostForm(callbackResult.getUrl(),
                formProperties);
        } catch (Exception e) {
            throw new BizException("xn000000", "回调业务biz异常");
        }
    }

}
