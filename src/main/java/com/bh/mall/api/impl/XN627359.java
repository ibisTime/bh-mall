package com.bh.mall.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627359Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询代理轨迹
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627359 extends AProcessor {

    private IYxFormAO yxFormAO = SpringContextHolder
        .getBean(IYxFormAO.class);

    private XN627359Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        YxForm condition = new YxForm();
        condition.setKeyWord(req.getKeyword());
        condition.setStatus(req.getStatus());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));

        Date approveDatetimeStart = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date approveDatetimeEnd = DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setApproveDatetimeStart(approveDatetimeStart);
        condition.setApproveDatetimeEnd(approveDatetimeEnd);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IYxFormAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return yxFormAO.queryAgentAllotPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627359Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
