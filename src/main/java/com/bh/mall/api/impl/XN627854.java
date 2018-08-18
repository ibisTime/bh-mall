package com.bh.mall.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IOrderReportAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.OrderReport;
import com.bh.mall.dto.req.XN627854Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 我的出货
 * @author: nyc 
 * @since: 2018年6月30日 下午7:10:33 
 * @history:
 */
public class XN627854 extends AProcessor {

    private IOrderReportAO ordeReportAO = SpringContextHolder
        .getBean(IOrderReportAO.class);

    private XN627854Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OrderReport condition = new OrderReport();
        condition.setApplyUser(req.getApplyUser());

        Date startDatetime = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date endDatetime = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IOrderReportAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return ordeReportAO.queryOrderReportPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627854Req.class);
        ObjValidater.validateReq(req);
    }

}
