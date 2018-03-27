package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627723Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品发货
 * @author: nyc 
 * @since: 2018年3月27日 下午5:35:50 
 * @history:
 */
public class XN627723 extends AProcessor {

    private IInnerOrderAO innerOrderAO = SpringContextHolder
        .getBean(IInnerOrderAO.class);

    private XN627723Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerOrderAO.deliverInnerProduct(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627723Req.class);
        ObjValidater.validateReq(req);
    }

}
