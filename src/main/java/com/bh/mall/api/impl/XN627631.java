package com.bh.mall.api.impl;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627631Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询购物车产品详情
 * @author: nyc 
 * @since: 2018年3月27日 下午2:24:04 
 * @history:
 */
public class XN627631 extends AProcessor {

    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN627631Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return cartAO.getCartProduct(req.getProductCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627631Req.class);
        ObjValidater.validateReq(req);
    }

}
