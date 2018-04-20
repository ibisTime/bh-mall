package com.bh.mall.api.impl;

import com.bh.mall.ao.IOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Order;
import com.bh.mall.dto.req.XN627663Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查订单
 * @author: nyc 
 * @since: 2018年3月28日 下午9:02:41 
 * @history:
 */
public class XN627663 extends AProcessor {

    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN627663Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Order condition = new Order();
        condition.setKeyword(req.getKeyword());
        condition.setKind(req.getKind());
        condition.setStatus(req.getStatus());
        condition.setProductName(req.getProductName());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        return orderAO.queryOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627663Req.class);

    }

}
