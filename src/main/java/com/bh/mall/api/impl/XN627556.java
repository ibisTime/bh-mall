package com.bh.mall.api.impl;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.Product;
import com.bh.mall.dto.req.XN627556Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 产品列表查询
 * @author: nyc 
 * @since: 2018年3月25日 下午4:42:37 
 * @history:
 */
public class XN627556 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN627556Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Product condition = new Product();
        condition.setName(req.getName());
        condition.setStatus(req.getStatus());
        return productAO.selectProductList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627556Req.class);
    }

}
