package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627704Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 下架产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:02:17 
 * @history:
 */
public class XN627704 extends AProcessor {

    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627704Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerProductAO.putdownInnerProduct(req.getCode(), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627704Req.class);
        ObjValidater.validateReq(req);
    }

}
