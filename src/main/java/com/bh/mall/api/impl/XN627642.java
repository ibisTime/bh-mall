package com.bh.mall.api.impl;

import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627642Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 支付订单
 * @author: nyc 
 * @since: 2018年3月28日 下午1:59:11 
 * @history:
 */
public class XN627642 extends AProcessor {

    private IOutOrderAO outOrderAO = SpringContextHolder
        .getBean(IOutOrderAO.class);

    private XN627642Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return outOrderAO.payOutOrder(req.getCodeList(), req.getPayType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627642Req.class);
        ObjValidater.validateReq(req);

    }

}
