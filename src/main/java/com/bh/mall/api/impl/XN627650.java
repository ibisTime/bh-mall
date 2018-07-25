package com.bh.mall.api.impl;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 订单作废
 * @author: nyc 
 * @since: 2018年3月28日 下午8:54:38 
 * @history:
 */

public class XN627650 extends AProcessor {

    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    // private XN627650Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // orderAO.invalidOrder(req.getCode(), req.getUpdater(),
        // req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // req = JsonUtil.json2Bean(inputparams, XN627650Req.class);
        // ObjValidater.validateReq(req);

    }

}
