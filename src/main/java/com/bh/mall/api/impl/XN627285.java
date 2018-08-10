package com.bh.mall.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISqFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SqForm;
import com.bh.mall.dto.req.XN627285Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询授权单
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627285 extends AProcessor {

    private ISqFormAO sqFormAO = SpringContextHolder.getBean(ISqFormAO.class);

    private XN627285Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        SqForm condition = new SqForm();
        condition.setKeyWord(req.getKeyword());
        condition.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());

        Date applyDatetimeStart = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date applyDatetimeEnd = DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setApplyDatetimeStart(applyDatetimeStart);
        condition.setApplyDatetimeEnd(applyDatetimeEnd);

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
        req = JsonUtil.json2Bean(inputparams, XN627285Req.class);
        ObjValidater.validateReq(req);
    }

}
