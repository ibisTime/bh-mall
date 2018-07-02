package com.bh.mall.api.impl;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627641Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 无购物车提交订单
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627641 extends AProcessor {

    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN627641Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(orderAO.addOrderNoCart(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627641Req.class);
        ObjValidater.validateReq(req);

    }

}
