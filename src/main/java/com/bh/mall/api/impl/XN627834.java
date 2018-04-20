package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IWareHouseLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.WareHouseLog;
import com.bh.mall.dto.req.XN627834Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *根据产品产流水
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627834 extends AProcessor {
    private IWareHouseLogAO wareHouseLogAO = SpringContextHolder
        .getBean(IWareHouseLogAO.class);

    private XN627834Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        WareHouseLog condition = new WareHouseLog();
        condition.setProductCode(req.getProductCode());
        condition.setStatus(req.getStatus());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IWareHouseLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return wareHouseLogAO.queryWareHouseLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627834Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getProductCode());

    }
}
