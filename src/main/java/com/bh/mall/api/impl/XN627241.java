package com.bh.mall.api.impl;

import com.bh.mall.ao.IJsAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627241Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改介绍奖
 * @author: nyc 
 * @since: 2018年6月28日 下午11:02:57 
 * @history:
 */
public class XN627241 extends AProcessor {
    private IJsAwardAO jsAwardAO = SpringContextHolder.getBean(IJsAwardAO.class);

    private XN627241Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        jsAwardAO.editJsAward(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627241Req.class);
        ObjValidater.validateReq(req);
    }

}
