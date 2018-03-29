package com.bh.mall.api.impl;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627622Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 批量删除购物车产品
 * @author: nyc 
 * @since: 2018年3月27日 下午2:12:27 
 * @history:
 */
public class XN627622 extends AProcessor {

    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN627622Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        cartAO.dropCart(req.getCodeList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627622Req.class);
        ObjValidater.validateReq(req);
    }

}
