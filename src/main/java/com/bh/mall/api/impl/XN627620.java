package com.bh.mall.api.impl;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627620Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 添加购物车
 * @author: nyc 
 * @since: 2018年3月27日 上午11:36:20 
 * @history:
 */
public class XN627620 extends AProcessor {

    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN627620Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(cartAO.addCart(req.getUserId(),
            req.getProductSpecsCode(), req.getQuantity()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627620Req.class);
        ObjValidater.validateReq(req);
    }

}
