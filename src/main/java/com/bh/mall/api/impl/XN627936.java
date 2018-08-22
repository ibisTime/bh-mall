package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IDeliveOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.DeliveOrder;
import com.bh.mall.dto.req.XN627936Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表发货订单
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:30:04 
 * @history:
 */
public class XN627936 extends AProcessor {

    private IDeliveOrderAO deliveOrderAO = SpringContextHolder
        .getBean(IDeliveOrderAO.class);

    private XN627936Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        DeliveOrder condition = new DeliveOrder();
        condition.setKeyword(req.getKeyword());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IDeliveOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return deliveOrderAO.queryDeliveOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627936Req.class);
    }
}
