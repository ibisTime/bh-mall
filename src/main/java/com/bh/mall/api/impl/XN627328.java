package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627328Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询代理轨迹
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627328 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627328Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        System.out.println("userId:" + req.getUserId());
        return agentAO.getAgent(req.getUserId());

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627328Req.class);
        ObjValidater.validateReq(req);
    }

}
