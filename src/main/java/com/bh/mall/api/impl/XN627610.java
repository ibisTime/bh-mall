package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISpecsLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.SpecsLog;
import com.bh.mall.dto.req.XN627610Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询库存记录
 * @author: nyc 
 * @since: 2018年3月26日 下午1:57:47 
 * @history:
 */
public class XN627610 extends AProcessor {

    private ISpecsLogAO specsLogAO = SpringContextHolder
        .getBean(ISpecsLogAO.class);

    private XN627610Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SpecsLog condition = new SpecsLog();
        condition.setSpecsCode(req.getProductCode());
        condition.setType(req.getType());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISpecsLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return specsLogAO.querySpecsLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627610Req.class);
        ObjValidater.validateReq(req);
    }

}
