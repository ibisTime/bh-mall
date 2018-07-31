package com.bh.mall.api.impl;

import com.bh.mall.ao.IExchangeOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.dto.req.XN627802Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *列表查询云仓申请单
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627802 extends AProcessor {
    private IExchangeOrderAO exchangeOrderAO = SpringContextHolder
        .getBean(IExchangeOrderAO.class);

    private XN627802Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ExchangeOrder condition = new ExchangeOrder();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setKeyword(req.getKeyword());

        return exchangeOrderAO.queryExchangeOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627802Req.class);

    }
}
