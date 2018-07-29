package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.ao.IWareLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.WareLog;
import com.bh.mall.dto.req.XN627831Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *分页查流水  front
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627831 extends AProcessor {
    private IWareLogAO wareLogAO = SpringContextHolder
        .getBean(IWareLogAO.class);

    private XN627831Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WareLog condition = new WareLog();
        condition.setKeyword(req.getKeyword());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getUserId());
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IInnerOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return wareLogAO.queryWareLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627831Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLevel(),
            req.getUserId());

    }
}
