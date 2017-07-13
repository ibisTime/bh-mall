package com.std.user.api.impl;

import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805952Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;
import com.std.user.third.wechat.impl.WechatTokenUtil;

/**
 * 获取微信JS-SDK使用权限签名等信息
 * @author: xieyj 
 * @since: 2017年3月29日 下午7:57:16 
 * @history:
 */
public class XN805952 extends AProcessor {
    private WechatTokenUtil wechatTokenUtil = SpringContextHolder
        .getBean(WechatTokenUtil.class);

    private XN805952Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return wechatTokenUtil.getSign(req.getSystemCode(),
            req.getCompanyCode(), req.getUrl());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805952Req.class);
        StringValidater.validateBlank(req.getSystemCode(),
            req.getCompanyCode(), req.getUrl());
    }
}
