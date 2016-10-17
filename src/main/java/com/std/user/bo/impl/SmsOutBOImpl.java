package com.std.user.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.user.bo.ISmsOutBO;
import com.std.user.dto.req.CD799001Req;
import com.std.user.dto.req.CD799002Req;
import com.std.user.dto.req.XN799001Req;
import com.std.user.dto.req.XN799002Req;
import com.std.user.dto.req.XN799003Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.dto.res.PKCodeRes;
import com.std.user.dto.res.XN799001Res;
import com.std.user.dto.res.XN799002Res;
import com.std.user.dto.res.XN799003Res;
import com.std.user.exception.BizException;
import com.std.user.http.BizConnecter;
import com.std.user.http.JsonUtils;

@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sendSmsOut(String mobile, String content, String bizType) {
        try {
            XN799001Req req = new XN799001Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setBizType(bizType);
            BizConnecter.getBizData("799001", JsonUtils.object2Json(req),
                XN799001Res.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }

    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType) {
        XN799002Req req = new XN799002Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        BizConnecter.getBizData("799002", JsonUtils.object2Json(req),
            XN799002Res.class);
    }

    @Override
    public void sendCaptcha(String mobile, String bizType) {
        try {
            XN799003Req req = new XN799003Req();
            req.setMobile(mobile);
            req.setBizType(bizType);
            BizConnecter.getBizData("799003", JsonUtils.object2Json(req),
                XN799003Res.class);
        } catch (Exception e) {
            logger.error("调用短信验证服务异常");
            throw new BizException("xn799003", "短信发送异常，请稍后再试");
        }
    }

    @Override
    public String sendSmsOut(String mobile, String content) {
        String code = null;
        try {
            CD799001Req req = new CD799001Req();
            req.setChannel("GJ1002-CSMD-K");
            req.setMobile(mobile);
            req.setContent(content);
            PKCodeRes res = BizConnecter.getBizData("799001",
                JsonUtils.object2Json(req), PKCodeRes.class);
            code = res.getCode();
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
        return code;
    }

    @Override
    public boolean checkCaptcha(String code, String captcha) {
        CD799002Req req = new CD799002Req();
        req.setCode(code);
        req.setCaptcha(captcha);
        BooleanRes res = BizConnecter.getBizData("799002",
            JsonUtils.object2Json(req), BooleanRes.class);
        return res.getIsSuccess();
    }

    @Override
    public String sendCaptcha(String mobile) {
        String code = null;
        try {
            CD799001Req req = new CD799001Req();
            req.setChannel("GJ1002-CSMD-K");
            req.setMobile(mobile);
            PKCodeRes res = BizConnecter.getBizData("799001",
                JsonUtils.object2Json(req), PKCodeRes.class);
            code = res.getCode();
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
        return code;
    }
}
