package com.bh.mall.api.impl;

import com.bh.mall.ao.IAfterSaleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627692Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情售后
 * @author: nyc 
 * @since: 2018年3月29日 下午1:22:00 
 * @history:
 */
public class XN627692 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN627692Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return afterSaleAO.getAfterSale(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627692Req.class);
        ObjValidater.validateReq(req);
    }

}
