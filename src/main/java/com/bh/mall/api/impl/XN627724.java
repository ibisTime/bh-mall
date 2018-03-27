package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627724Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627724 extends AProcessor {

    private IInnerOrderAO innerOrderAO = SpringContextHolder
        .getBean(IInnerOrderAO.class);

    private XN627724Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerOrderAO.cancelInnerOrder(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627724Req.class);
        ObjValidater.validateReq(req);
    }
}
