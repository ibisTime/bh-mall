package com.bh.mall.api.impl;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627664Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 订单详情
 * @author: nyc 
 * @since: 2018年4月8日 上午11:36:43 
 * @history:
 */
public class XN627664 extends AProcessor {

    private IInOrderAO inOrderAO = SpringContextHolder.getBean(IInOrderAO.class);

    private XN627664Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return inOrderAO.getInOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627664Req.class);
        ObjValidater.validateReq(req);

    }

}
