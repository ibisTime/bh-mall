package com.bh.mall.api.impl;

import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627640Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 提交订单
 * @author: nyc 
 * @since: 2018年3月28日 下午1:55:54 
 * @history:
 */
public class XN627651 extends AProcessor {

    private IOutOrderAO outOrderAO = SpringContextHolder
        .getBean(IOutOrderAO.class);

    private XN627640Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return outOrderAO.addOutOrderC(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627640Req.class);
        ObjValidater.validateReq(req);

    }

}
