package com.bh.mall.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SjForm;
import com.bh.mall.dto.req.XN627292Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查代理轨迹
 * @author: nyc 
 * @since: 2018年3月29日 下午6:28:14 
 * @history:
 */

public class XN627292 extends AProcessor {

    private ISjFormAO sjFormAO = SpringContextHolder
        .getBean(ISjFormAO.class);

    private XN627292Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SjForm condition = new SjForm();
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
            column = ISjFormAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return sjFormAO.querySjFormPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627292Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
