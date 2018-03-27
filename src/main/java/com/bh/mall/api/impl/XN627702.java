package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627702Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 删除内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:14:53 
 * @history:
 */
public class XN627702 extends AProcessor {

    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627702Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerProductAO.dropInnerProduct(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627702Req.class);
        ObjValidater.validateReq(req);
    }

}
