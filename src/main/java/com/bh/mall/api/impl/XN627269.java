package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627269Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 忽略意向分配
 * @author: clockorange 
 * @since: Jul 16, 2018 4:37:13 PM 
 * @history:
 */

public class XN627269 extends AProcessor {

    private IYxFormAO userAO = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627269Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.ignore(req.getUserId(), req.getApprover());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627269Req.class);
        ObjValidater.validateReq(req);
    }
}
