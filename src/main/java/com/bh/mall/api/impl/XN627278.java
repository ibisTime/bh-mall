package com.bh.mall.api.impl;

import com.bh.mall.ao.IAgentAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627278Req;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理微信注册/登录
 * @author: clockorange 
 * @since: Jul 17, 2018 10:32:12 AM 
 * @history:
 */

public class XN627278 extends AProcessor {

    private IAgentAO agentAO = SpringContextHolder.getBean(IAgentAO.class);

    private XN627278Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return agentAO.doLoginWeChatByMerchant(req.getCode(),
            EUserKind.Merchant.getCode(), req.getUserReferee());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627278Req.class);
        ObjValidater.validateReq(req);
    }

}
