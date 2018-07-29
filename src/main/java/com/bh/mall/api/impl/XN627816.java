package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IWareAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Ware;
import com.bh.mall.dto.req.XN627816Req;
import com.bh.mall.enums.EProductStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * C端查询
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627816 extends AProcessor {

    private IWareAO wareAO = SpringContextHolder
        .getBean(IWareAO.class);

    private XN627816Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Ware condition = new Ware();
        condition.setUserId(req.getUserId());
        condition.setStatus(EProductStatus.Shelf_YES.getCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IWareAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return wareAO.queryWareCFrontPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627816Req.class);
    }

}
