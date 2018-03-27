package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.dto.req.XN627712Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:27:32 
 * @history:
 */
public class XN627712 extends AProcessor {
    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627712Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return innerProductAO.getInnerProduct(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627712Req.class);
    }
}
