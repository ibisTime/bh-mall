package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN6272551Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * update information
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN6272551 extends AProcessor {
    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN6272551Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        agentAO.editInformation(req.getAddress(), req.getArea(), req.getCity(),
            req.getMobile(), req.getProvince(), req.getRealName(),
            req.getTeamName(), req.getUserId(), req.getWxId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN6272551Req.class);
        ObjValidater.validateReq(req);
    }

}
