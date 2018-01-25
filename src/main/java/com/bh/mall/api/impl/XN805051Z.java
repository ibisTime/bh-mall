package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805051ZReq;
import com.bh.mall.dto.res.XN805051ZRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

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
