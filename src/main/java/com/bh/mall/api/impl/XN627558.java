package com.bh.mall.api.impl;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627558Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 产品详情
 * @author: nyc 
 * @since: 2018年3月25日 下午4:42:58 
 * @history:
 */
public class XN627558 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN627558Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return productAO.getProduct(req.getCode(),
            StringValidater.toInteger(req.getLevel()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627558Req.class);
        ObjValidater.validateReq(req);
    }

}
