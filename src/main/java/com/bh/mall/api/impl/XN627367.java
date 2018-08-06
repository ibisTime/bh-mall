package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627367Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询代理轨迹
 * @author: nyc 
 * @since: 2018年8月6日 下午8:50:31 
 * @history:
 */
public class XN627367 extends AProcessor {

    private IAgentLogAO agentLogAO = SpringContextHolder
        .getBean(IAgentLogAO.class);

    private XN627367Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return agentLogAO.getAgentLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627367Req.class);
        ObjValidater.validateReq(req);
    }

}
