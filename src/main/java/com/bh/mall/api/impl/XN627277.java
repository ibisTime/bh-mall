package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627277Req;
import com.bh.mall.dto.res.XN627300Res;
import com.bh.mall.enums.EUserStatus;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理 普通登录
 * @author: clockorange 
 * @since: Jul 17, 2018 10:19:32 AM 
 * @history:
 */

public class XN627277 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627277Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627300Res(agentAO.doLogin(req.getLoginName(),
            req.getLoginPwd(), req.getKind()), EUserStatus.ALLOTED.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627277Req.class);
        ObjValidater.validateReq(req);
    }
}
