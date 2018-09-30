package com.bh.mall.api.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.ao.IOutOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.bo.base.Page;
import com.bh.mall.bo.base.Paginable;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.InOrder;
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

    private IInOrderAO inOrderAO = SpringContextHolder
        .getBean(IInOrderAO.class);

    private XN627662Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        List<Object> list = new LinkedList<Object>();
        OutOrder condition = new OutOrder();
        condition.setKeyword(req.getKeyword());
        condition.setKind(req.getKind());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));

        condition.setTeamName(req.getTeamName());
        condition.setIsWareSend(req.getIsWareSend());
        condition.setDeliver(req.getDeliver());
        condition.setProductName(req.getProductName());
        condition.setToUserId(req.getToUser());
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
        Paginable<OutOrder> outPage = outOrderAO.queryOutOrderPage(start,
            limit / 2, condition);
        list.addAll(outPage.getList());

        InOrder inCondition = new InOrder();

        inCondition.setKeyword(req.getKeyword());
        inCondition.setStatus(req.getStatus());
        inCondition.setStatusList(req.getStatusList());
        inCondition.setLevel(StringValidater.toInteger(req.getLevel()));

        inCondition.setTeamName(req.getTeamName());
        inCondition.setProductName(req.getProductName());
        inCondition.setToUserId(req.getToUser());
        inCondition.setLevel(StringValidater.toInteger(req.getLevel()));

        inCondition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        inCondition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IInOrderAO.DEFAULT_ORDER_COLUMN;
        }
        inCondition.setOrder(column, req.getOrderDir());

        Paginable<InOrder> inPage = inOrderAO.queryInOrderPage(start, limit / 2,
            inCondition);
        list.addAll(inPage.getList());

        Paginable<Object> page = new Page<Object>(start, limit,
            outPage.getTotalCount() + inPage.getTotalCount());
        page.setList(list);
        return page;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627662Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
