package com.bh.mall.api.impl;

import com.bh.mall.ao.ITjAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627580Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627580 extends AProcessor {

    private ITjAwardAO awardAO = SpringContextHolder.getBean(ITjAwardAO.class);

    private XN627580Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        awardAO.editTjAward(req.getCode(), req.getValue1(), req.getValue2(),
            req.getValue3());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627580Req.class);
        ObjValidater.validateReq(req);
    }

}
