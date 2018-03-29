package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627703Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品上架
 * @author: nyc 
 * @since: 2018年3月26日 下午2:45:59 
 * @history:
 */
public class XN627703 extends AProcessor {

    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627703Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerProductAO.putOnInnerProduct(req.getCode(), req.getOrderNo(),
            req.getIsFree(), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627703Req.class);
        ObjValidater.validateReq(req);
    }

}
