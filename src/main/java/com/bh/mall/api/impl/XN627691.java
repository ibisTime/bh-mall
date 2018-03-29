package com.bh.mall.api.impl;

import com.bh.mall.ao.IAfterSaleAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.AfterSale;
import com.bh.mall.dto.req.XN627691Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查售后
 * @author: nyc 
 * @since: 2018年3月29日 上午11:46:29 
 * @history:
 */
public class XN627691 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN627691Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AfterSale condition = new AfterSale();
        condition.setApplyUser(req.getApplyUser());
        condition.setStatus(req.getStatus());
        condition.setSaleType(req.getType());
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));
        return afterSaleAO.queryAfterSaleList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627691Req.class);
    }

}
