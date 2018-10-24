package com.xn.sdhh.api.impl;

import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627092Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;
import com.xn.sdhh.third.wechat.impl.WechatTokenUtil;

/**
 * 获取微信JS-SDK使用权限签名等信息
 * @author: xieyj 
 * @since: 2017年3月29日 下午7:57:16 
 * @history:
 */
public class XN627092 extends AProcessor {
    private WechatTokenUtil wechatTokenUtil = SpringContextHolder
        .getBean(WechatTokenUtil.class);

    private XN627092Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return wechatTokenUtil.getSign(req.getSystemCode(),
            req.getCompanyCode(), req.getUrl());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627092Req.class);
        ObjValidater.validateReq(req);
    }
}
