package com.std.user.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.user.bo.ISmsOutBO;
import com.std.user.common.PropertiesUtil;
import com.std.user.dto.req.XN799001Req;
import com.std.user.dto.req.XN799003Req;
import com.std.user.dto.req.XN799007Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.dto.res.PKCodeRes;
import com.std.user.http.BizConnecter;
import com.std.user.http.JsonUtils;

@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sendCaptcha(String mobile, String bizType, String kind,
            String systemCode) {
        try {
            XN799003Req req = new XN799003Req();
            req.setChannel(PropertiesUtil.Config.SMS_CHANNEL + "-K");
            req.setMobile(mobile);
            req.setBizType(bizType);
            BizConnecter.getBizData("799003", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType) {
        XN799007Req req = new XN799007Req();
        String[] channels = PropertiesUtil.Config.SMS_CHANNEL.split("-");
        req.setCompanyCode(channels[0]);
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        BizConnecter.getBizData("799007", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void sendSmsOut(String mobile, String content, String bizType) {
        try {
            XN799001Req req = new XN799001Req();
            req.setChannel(PropertiesUtil.Config.SMS_CHANNEL + "-M");
            req.setMobile(mobile);
            req.setContent(content);
            BizConnecter.getBizData("799001", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }

    @Override
    public void sendSmsOut(String mobile, String content, String bizType,
            String companyCode) {
        try {
            XN799001Req req = new XN799001Req();
            req.setChannel(PropertiesUtil.Config.SMS_CHANNEL + "-M");
            req.setMobile(mobile);
            req.setContent(content);
            req.setCompanyCode(companyCode);
            BizConnecter.getBizData("799001", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }
}
