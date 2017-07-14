package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805051ZReq;
import com.std.user.dto.res.XN805051ZRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 验证码登录注册
 * @author: xieyj 
 * @since: 2017年1月19日 下午5:52:48 
 * @history:
 */
public class XN805051Z extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805051ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805051ZRes(userAO.doCaptchaLoginReg(req.getMobile(),
            req.getKind(), req.getSmsCaptcha(), req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805051ZReq.class);
        StringValidater.validateBlank(req.getMobile(), req.getKind(),
            req.getSmsCaptcha(), req.getSystemCode());
    }
}
