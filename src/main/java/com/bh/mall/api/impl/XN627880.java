package com.bh.mall.api.impl;

import com.bh.mall.ao.ISecurityTraceAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627880Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询防伪码
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627880 extends AProcessor {

    private ISecurityTraceAO securityTraceAO = SpringContextHolder
        .getBean(ISecurityTraceAO.class);

    private XN627880Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return securityTraceAO.getSecurity(req.getSecurityCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627880Req.class);
        ObjValidater.validateReq(req);
    }

}
