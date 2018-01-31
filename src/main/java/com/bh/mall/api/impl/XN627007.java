package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627007Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询代理详情
 * @author: nyc 
 * @since: 2018年1月31日 下午2:49:17 
 * @history:
 */
public class XN627007 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627007Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return agentAO.getAgent(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627007Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
