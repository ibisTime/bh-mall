package com.bh.mall.api.impl;

import com.bh.mall.ao.IAfterSaleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627682Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 回寄发货
 * @author: nyc 
 * @since: 2018年3月29日 上午11:24:47 
 * @history:
 */
public class XN627682 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN627682Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        afterSaleAO.changeProduct(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627682Req.class);
        ObjValidater.validateReq(req);
    }

}
