package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IJsAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.JsAward;
import com.bh.mall.dto.req.XN627246Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询介绍奖励
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:33:35 
 * @history:
 */
public class XN627246 extends AProcessor {
    private IJsAwardAO jsAwardAO = SpringContextHolder
        .getBean(IJsAwardAO.class);

    private XN627246Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        JsAward condition = new JsAward();
        condition.setCode(req.getCode());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IJsAwardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return jsAwardAO.queryJsAwardList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627246Req.class);
    }

}
