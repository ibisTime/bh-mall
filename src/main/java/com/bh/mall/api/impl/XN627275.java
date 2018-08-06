package com.bh.mall.api.impl;

import com.bh.mall.ao.ISqFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627275Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 取消代理授权（oss）
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627275 extends AProcessor {

    private ISqFormAO sqFormAO = SpringContextHolder.getBean(ISqFormAO.class);

    private XN627275Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sqFormAO.cancelSqFormByP(req.getUserId(), req.getApprover(),
            req.getResult(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627275Req.class);
        ObjValidater.validateReq(req);
    }

}