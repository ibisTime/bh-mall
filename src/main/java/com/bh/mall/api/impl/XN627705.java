package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627705Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627705 extends AProcessor {
    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627705Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerProductAO.changeQuantity(req.getCode(), req.getType(),
            req.getQuantity(), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627705Req.class);
        ObjValidater.validateReq(req);
    }
}
