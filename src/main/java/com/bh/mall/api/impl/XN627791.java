package com.bh.mall.api.impl;

import com.bh.mall.ao.IExchangeOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627791Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改换货价
 * @author: nyc 
 * @since: 2018年4月10日 下午5:46:41 
 * @history:
 */
public class XN627791 extends AProcessor {

    private IExchangeOrderAO exchangeOrderAO = SpringContextHolder
        .getBean(IExchangeOrderAO.class);

    private XN627791Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        exchangeOrderAO.editChangePrice(req.getCode(), req.getChangePrice(),
            req.getApprover(), req.getApproveNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627791Req.class);
        ObjValidater.validateReq(req);

    }

}
