package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627230Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 获取代理详情
 * @author: nyc 
 * @since: 2018年7月29日 下午5:16:47 
 * @history:
 */
public class XN627230 extends AProcessor {
    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627230Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return agentAO.getAgent(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627230Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
