package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.dto.req.XN627329Req;
import com.bh.mall.enums.EIsTrader;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询操盘手
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627329 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627329Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Agent condition = new Agent();
        condition.setUserId(req.getUserId());
        if (StringUtils.isBlank(req.getUserId())) {
            condition.setIsTrader(EIsTrader.TRADER_YES.getCode());
        }
        condition.setLevel(1);
        return agentAO.queryTrader(condition);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627329Req.class);
        ObjValidater.validateReq(req);
    }

}
