package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627722Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改收货地址以及运费
 * @author: chenshan 
 * @since: 2018年3月29日 下午7:57:14 
 * @history:
 */
public class XN627722 extends AProcessor {

    private IInnerOrderAO innerOrderAO = SpringContextHolder
        .getBean(IInnerOrderAO.class);

    private XN627722Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        innerOrderAO.refreshAddress(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627722Req.class);
        ObjValidater.validateReq(req);
    }

}
