package com.bh.mall.api.impl;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627790 extends AProcessor {

    private IChangeProductAO changeProductAO = SpringContextHolder
        .getBean(IChangeProductAO.class);

    private XN627790Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(changeProductAO.addChangeProduct(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627790Req.class);
        ObjValidater.validateReq(req);

    }

}
