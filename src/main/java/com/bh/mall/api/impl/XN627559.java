package com.bh.mall.api.impl;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627559Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 根据规格查询产品详情
 * @author: nyc 
 * @since: 2018年3月25日 下午4:42:58 
 * @history:
 */
public class XN627559 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN627559Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return productAO.getProductBySpecs(req.getSpecsCode(), req.getLevel());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627559Req.class);
        ObjValidater.validateReq(req);
    }

}
