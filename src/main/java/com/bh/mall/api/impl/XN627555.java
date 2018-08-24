package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Product;
import com.bh.mall.dto.req.XN627555Req;
import com.bh.mall.enums.EBoolean;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页产品
 * @author: nyc 
 * @since: 2018年3月25日 下午3:40:25 
 * @history:
 */
public class XN627555 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN627555Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Product condition = new Product();
        condition.setStatus(req.getStatus());
        condition.setName(req.getName());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));

        condition.setIsBuy(EBoolean.YES.getCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProductAO.DEFAULT_ORDER_COLUMN;
        }

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        condition.setOrder(column, req.getOrderDir());
        return productAO.selectProductPageByB(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627555Req.class);
        ObjValidater.validateReq(req);
    }

}
