package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.ao.IWareAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Specs;
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

    private ISpecsAO specsAO = SpringContextHolder.getBean(ISpecsAO.class);

    private XN627816Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Specs condition = new Specs();

        condition.setStatus(EProductStatus.Shelf_YES.getCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IWareAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return specsAO.queryProductCFrontPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627816Req.class);
    }

}
