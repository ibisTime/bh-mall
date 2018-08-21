package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.InnerProduct;
import com.bh.mall.dto.req.XN627710Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询内购产品
 * @author: nyc 
 * @since: 2018年3月26日 下午3:25:41 
 * @history:
 */
public class XN627711 extends AProcessor {

    private IInnerProductAO innerProductAO = SpringContextHolder
        .getBean(IInnerProductAO.class);

    private XN627710Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        InnerProduct condition = new InnerProduct();
        condition.setName(req.getName());
        condition.setStatus(req.getStatus());
        return innerProductAO.queryInnerProductList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627710Req.class);
    }
}
