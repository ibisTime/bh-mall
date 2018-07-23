package com.bh.mall.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IUpLevelApplyAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.UpLevelApply;
import com.bh.mall.dto.req.XN627291Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询意向代理
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */

public class XN627291 extends AProcessor {

    private IUpLevelApplyAO agentAllotAO = SpringContextHolder
        .getBean(IUpLevelApplyAO.class);

    private XN627291Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UpLevelApply condition = new UpLevelApply();
        condition.setKeyWord(req.getKeyword());
        condition.setUserIdForQuery(req.getUserId());
        // condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        condition.setStatus(req.getStatus());

        Date applyDatetimeStart = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date applyDatetimeEnd = DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setApplyDatetimeStart(applyDatetimeStart);
        condition.setApplyDatetimeEnd(applyDatetimeEnd);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUpLevelApplyAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return agentAllotAO.queryIntentionAgentFrontPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627291Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit(),
            req.getUserId());
    }

}
