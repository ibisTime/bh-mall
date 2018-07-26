package com.bh.mall.api.impl;

import com.bh.mall.ao.IMiniCodeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627881Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询溯源
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627881 extends AProcessor {

    private IMiniCodeAO securityTraceAO = SpringContextHolder
        .getBean(IMiniCodeAO.class);

    private XN627881Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return securityTraceAO.getTrace(req.getTraceCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627881Req.class);
        ObjValidater.validateReq(req);
    }

}
