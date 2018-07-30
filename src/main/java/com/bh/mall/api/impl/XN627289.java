package com.bh.mall.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISqFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627289Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查轨迹
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */

public class XN627289 extends AProcessor {

    private ISqFormAO sqFormAO = SpringContextHolder
        .getBean(ISqFormAO.class);

    private XN627289Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SqForm condition = new SqForm();
        condition.setKeyWord(req.getKeyword());
        condition.setStatus(req.getStatus());
        // condition.setLevel(StringValidater.toInteger(req.getLevel()));

        Date approveDatetimeStart = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date approveDatetimeEnd = DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setApproveDatetimeStart(approveDatetimeStart);
        condition.setApproveDatetimeEnd(approveDatetimeEnd);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISqFormAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return sqFormAO.querySqFormPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627289Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
