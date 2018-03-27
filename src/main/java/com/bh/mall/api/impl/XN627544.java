package com.bh.mall.api.impl;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627544Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 产品下架
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:23 
 * @history:
 */
public class XN627544 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN627544Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        productAO.putdownProduct(req.getCode(), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627544Req.class);
        ObjValidater.validateReq(req);
    }

}
