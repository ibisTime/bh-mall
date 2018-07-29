package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627303Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 注销/激活用户 (代理）
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:10:36 
 * @history:
 */
public class XN627303 extends AProcessor {
    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627303Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        agentAO.doCloseOpen(req.getUserId(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627303Req.class);
        ObjValidater.validateReq(req);
    }

}
