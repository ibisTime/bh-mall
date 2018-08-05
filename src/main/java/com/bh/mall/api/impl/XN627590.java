package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.ITjAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.TjAward;
import com.bh.mall.dto.req.XN627590Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询奖励
 * @author: nyc 
 * @since: 2018年3月26日 上午10:59:51 
 * @history:
 */
public class XN627590 extends AProcessor {

    private ITjAwardAO awardAO = SpringContextHolder.getBean(ITjAwardAO.class);

    private XN627590Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TjAward condition = new TjAward();
        condition.setProductCode(req.getProductCode());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ITjAwardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return awardAO.queryTjAwardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627590Req.class);
        ObjValidater.validateReq(req);
    }

}
