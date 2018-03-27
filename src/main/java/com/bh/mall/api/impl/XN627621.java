package com.bh.mall.api.impl;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627621Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改购物车数量
 * @author: nyc 
 * @since: 2018年3月27日 上午11:48:31 
 * @history:
 */
public class XN627621 extends AProcessor {
    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN627621Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        cartAO.editCart(req.getCode(), req.getType(), req.getQuantity());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627621Req.class);
        ObjValidater.validateReq(req);
    }
}
