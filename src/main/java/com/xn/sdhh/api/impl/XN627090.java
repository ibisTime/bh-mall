package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISmsOutAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.common.PhoneUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627090Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2016年10月17日 下午7:23:10 
 * @history:
 */
public class XN627090 extends AProcessor {

    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN627090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendCaptcha(req.getMobile(), req.getBizType(),
            req.getCompanyCode(), req.getSystemCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627090Req.class);
        ObjValidater.validateReq(req);
        PhoneUtil.checkMobile(req.getMobile(), "请输入正确的手机号码");
    }
}
