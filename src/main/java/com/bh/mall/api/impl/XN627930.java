package com.bh.mall.api.impl;

import com.bh.mall.ao.IDeliveOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627930Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 发货
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627930 extends AProcessor {
    private IDeliveOrderAO deliveOrderAO = SpringContextHolder
        .getBean(IDeliveOrderAO.class);

    private XN627930Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        deliveOrderAO.deliverOrder(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627930Req.class);
        ObjValidater.validateReq(req);

    }

}
