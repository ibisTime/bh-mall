package com.bh.mall.api.impl;

import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627666Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 获取运费
 * @author: nyc 
 * @since: 2018年3月28日 下午9:02:41 
 * @history:
 */
public class XN627666 extends AProcessor {

    private IOutOrderAO outOrderAO = SpringContextHolder
        .getBean(IOutOrderAO.class);

    private XN627666Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return outOrderAO.getYunFei(req.getProductCode(), req.getSpecsCode(),
            req.getProvince(), req.getQuantity(), req.getKind());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627666Req.class);
        ObjValidater.validateReq(req);

    }

}
