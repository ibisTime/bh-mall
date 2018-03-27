package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ICartAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Cart;
import com.bh.mall.dto.req.XN627630Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询购物车产品
 * @author: nyc 
 * @since: 2018年3月27日 下午2:13:09 
 * @history:
 */
public class XN627630 extends AProcessor {

    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN627630Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Cart condition = new Cart();
        condition.setUserId(req.getUserId());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICartAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cartAO.queryCartPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627630Req.class);
        ObjValidater.validateReq(req);
    }

}
