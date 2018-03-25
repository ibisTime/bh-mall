package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627002Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改代理
 * @author: nyc 
 * @since: 2018年1月31日 下午2:36:28 
 * @history:
 */
public class XN627002 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        agentAO.editAgent(req.getLevel(), req.getName(), req.getAmount(),
            req.getMinChargeAmount(), req.getRedAmount(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627002Req.class);
        ObjValidater.validateReq(req);
    }

}
