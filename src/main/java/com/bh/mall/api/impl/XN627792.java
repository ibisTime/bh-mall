package com.bh.mall.api.impl;

import com.bh.mall.ao.IExchangeProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627792Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 审核置换单
 * @author: nyc 
 * @since: 2018年4月10日 下午5:47:00 
 * @history:
 */
public class XN627792 extends AProcessor {

    private IExchangeProductAO exchangeProductAO = SpringContextHolder
        .getBean(IExchangeProductAO.class);

    private XN627792Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        exchangeProductAO.approveChange(req.getCode(), req.getApprover(),
            req.getApproveNote(), req.getResult());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627792Req.class);
        ObjValidater.validateReq(req);

    }

}
