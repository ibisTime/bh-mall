package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.ao.IWareHouseLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.WareHouseLog;
import com.bh.mall.dto.req.XN627830Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *分页查流水
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627830 extends AProcessor {
    private IWareHouseLogAO wareHouseLogAO = SpringContextHolder
        .getBean(IWareHouseLogAO.class);

    private XN627830Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WareHouseLog condition = new WareHouseLog();
        condition.setStatus(req.getStatus());
        condition.setKeyword(req.getKeyword());

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

        return wareHouseLogAO.queryWareHouseLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627830Req.class);

    }
}
