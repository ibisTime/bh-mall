package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Agent;
import com.bh.mall.dto.req.XN627008Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询列表代理
 * @author: nyc 
 * @since: 2018年1月31日 下午2:52:34 
 * @history:
 */
public class XN627008 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627008Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Agent condition = new Agent();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setName(req.getName());
        condition.setLowLevel(StringValidater.toInteger(req.getLowLevel()));
        condition.setHighLevel(StringValidater.toInteger(req.getHighLevel()));

        return agentAO.queryAgentNoCList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627008Req.class);
    }

}
