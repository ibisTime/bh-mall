package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IWareHouseAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.WareHouse;
import com.bh.mall.dto.req.XN627811Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询云仓产品/front
 * @author: nyc 
 * @since: 2018年4月4日 下午5:27:32 
 * @history:
 */
public class XN627811 extends AProcessor {

    private IWareHouseAO wareHouseAO = SpringContextHolder
        .getBean(IWareHouseAO.class);

    private XN627811Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WareHouse condition = new WareHouse();
        condition.setType(req.getType());
        condition.setKeyword(req.getKeyword());
        condition.setUserId(req.getUserId());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IWareHouseAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return wareHouseAO.queryWareHouseFrontPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627811Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit(),
            req.getUserId());
    }

}
