package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentImpowerAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627017Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询代理授权详情
 * @author nyc
 *
 */
public class XN627017 extends AProcessor {

    private IAgentImpowerAO agentImpowerAO = SpringContextHolder
        .getBean(IAgentImpowerAO.class);

    private XN627017Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return agentImpowerAO.getAgentImpower(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627017Req.class);
        ObjValidater.validateReq(req);
    }

}
