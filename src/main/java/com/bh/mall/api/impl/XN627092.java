package com.bh.mall.api.impl;

import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627092Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;
import com.bh.mall.third.wechat.impl.WechatTokenUtil;

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
