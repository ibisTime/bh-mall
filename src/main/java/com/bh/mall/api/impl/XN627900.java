package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627900Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品规格-新增
 * @author: LENOVO 
 * @since: 2018年8月1日 下午3:56:45 
 * @history:
 */
public class XN627900 extends AProcessor {
    private IInnerSpecsAO innerSpecsAO = SpringContextHolder
        .getBean(IInnerSpecsAO.class);

    private XN627900Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(innerSpecsAO.addInnerSpecs(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627900Req.class);
        ObjValidater.validateReq(req);
    }

}
