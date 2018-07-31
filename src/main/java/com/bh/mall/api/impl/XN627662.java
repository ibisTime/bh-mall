package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.OutOrder;
import com.bh.mall.dto.req.XN627662Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询订单
 * @author: nyc 
 * @since: 2018年3月28日 下午9:02:41 
 * @history:
 */
public class XN627662 extends AProcessor {

    private IOutOrderAO outOrderAO = SpringContextHolder
        .getBean(IOutOrderAO.class);

    private XN627662Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OutOrder condition = new OutOrder();
        condition.setKeyword(req.getKeyword());
        condition.setKind(req.getKind());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());

        condition.setDeliver(req.getDeliver());
        condition.setProductName(req.getProductName());
        condition.setToUser(req.getToUser());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));

        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IOutOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return outOrderAO.queryOutOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627662Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
