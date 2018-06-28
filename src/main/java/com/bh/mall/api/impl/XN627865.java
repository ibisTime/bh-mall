package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAwardIntervalAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AwardInterval;
import com.bh.mall.dto.req.XN627865Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询介绍奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:55:07 
 * @history:
 */
public class XN627865 extends AProcessor {

    private IAwardIntervalAO awardIntervalAO = SpringContextHolder
        .getBean(IAwardIntervalAO.class);

    private XN627865Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AwardInterval condition = new AwardInterval();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAwardIntervalAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return awardIntervalAO.queryAwardIntervalPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627865Req.class);
        ObjValidater.validateReq(req);
    }

}
