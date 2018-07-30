package com.bh.mall.api.impl;

import com.bh.mall.ao.ISpecsLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627612Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627612 extends AProcessor {

    private ISpecsLogAO productLogAO = SpringContextHolder
        .getBean(ISpecsLogAO.class);

    private XN627612Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return productLogAO.getSpecsLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627612Req.class);
        ObjValidater.validateReq(req);

    }

}
