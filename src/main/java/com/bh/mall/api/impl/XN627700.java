package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627700Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午2:17:57 
 * @history:
 */
public class XN627700 extends AProcessor {

    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627700Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(innerProductAO.addInnerProduct(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627700Req.class);
        ObjValidater.validateReq(req);
    }

}
