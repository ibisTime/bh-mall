package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISignLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SignLog;
import com.bh.mall.dto.req.XN805145Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 分页查询签到记录
 * @author: zuixian 
 * @since: 2016年9月19日 下午1:34:23 
 * @history:
 */
public class XN805145 extends AProcessor {

    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805145Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SignLog condition = new SignLog();
        condition.setUserId(req.getUserId());
        condition.setSignDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setSignDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISignLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return signLogAO.querySignLogPage(condition, start, limit);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805145Req.class);
        StringValidater.validateBlank(req.getUserId());
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
