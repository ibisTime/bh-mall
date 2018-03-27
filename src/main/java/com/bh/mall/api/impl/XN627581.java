package com.bh.mall.api.impl;

import com.bh.mall.ao.IAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627581Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627581 extends AProcessor {

    private IAwardAO awardAO = SpringContextHolder.getBean(IAwardAO.class);

    private XN627581Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        awardAO.editAward(req.getCode(), req.getValue1(), req.getValue2(),
            req.getValue3());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627581Req.class);
        ObjValidater.validateReq(req);
    }

}
