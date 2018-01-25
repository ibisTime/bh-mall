package com.bh.mall.api.impl;

import com.bh.mall.ao.ISmsOutAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805950Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2016年10月17日 下午7:23:10 
 * @history:
 */
public class XN805950 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN805950Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendCaptcha(req.getMobile(), req.getBizType(),
            req.getCompanyCode(), req.getSystemCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805950Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getBizType(),
            req.getCompanyCode(), req.getSystemCode());
        PhoneUtil.checkMobile(req.getMobile(), "请输入正确的手机号码");
    }
}
