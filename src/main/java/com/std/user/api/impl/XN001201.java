package com.std.user.api.impl;

import com.std.user.ao.ISmsOutAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.common.PhoneUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN00120Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2016年10月17日 下午7:23:10 
 * @history:
 */
public class XN001201 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN00120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendCaptcha(req.getMobile(), req.getBizType(), req.getKind(),
            req.getSystemCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN00120Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getBizType(),
            req.getKind(), req.getSystemCode());
        PhoneUtil.checkMobile(req.getMobile(), "请输入正确的手机号码");
    }
}
