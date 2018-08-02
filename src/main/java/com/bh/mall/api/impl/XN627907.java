package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627907Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品规格-详情
 * @author: LENOVO 
 * @since: 2018年8月2日 上午10:14:01 
 * @history:
 */
public class XN627907 extends AProcessor {

    private IInnerSpecsAO innerSpecsAO = SpringContextHolder
        .getBean(IInnerSpecsAO.class);

    private XN627907Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return innerSpecsAO.getInnerSpecs(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627907Req.class);
        ObjValidater.validateReq(req);
    }

}
