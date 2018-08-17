package com.bh.mall.api.impl;

import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627928Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 规格库存
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627928 extends AProcessor {

    private ISpecsAO specsAO = SpringContextHolder.getBean(ISpecsAO.class);

    private XN627928Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        specsAO.getSpecsByLevel(req.getCode(), req.getLevel());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627928Req.class);
        ObjValidater.validateReq(req);
    }

}
