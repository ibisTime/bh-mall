package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IExchangeOrderAO;
import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ExchangeOrder;
import com.bh.mall.dto.req.XN627801Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *分页查询云仓申请单/front
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627801 extends AProcessor {
    private IExchangeOrderAO exchangeOrderAO = SpringContextHolder
        .getBean(IExchangeOrderAO.class);

    private XN627801Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ExchangeOrder condition = new ExchangeOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setKeyword(req.getKeyword());
        condition.setApplyStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setApplyEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IInnerOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

<<<<<<< HEAD
        return exchangeOrderAO.queryExchangeOrderPage(start, limit, condition);
=======
        return exchangeOrderAO.queryChangeOrderPage(start, limit, condition);
>>>>>>> refs/remotes/origin/master
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627801Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit(),
            req.getApplyUser());

    }
}
