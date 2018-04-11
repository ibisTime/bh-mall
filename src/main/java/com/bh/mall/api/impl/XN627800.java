package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.dto.req.XN627800Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *分页查询云仓申请单
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627800 extends AProcessor {
    private IChangeProductAO changeProductAO = SpringContextHolder
        .getBean(IChangeProductAO.class);

    private XN627800Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ChangeProduct condition = new ChangeProduct();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setKeyword(req.getKeyword());
        condition.setStatus(req.getStatus());
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

        return changeProductAO.queryChangeProductPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627800Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }
}
