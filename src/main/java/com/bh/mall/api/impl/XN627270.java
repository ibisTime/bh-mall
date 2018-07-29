package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627270Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 意向分配
 * @author: clockorange 
 * @since: Jul 16, 2018 4:47:28 PM 
 * @history:
 */

public class XN627270 extends AProcessor {
    private IYxFormAO userAO = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627270Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.allotAgent(req.getUserId(), req.getToUserId(),
            req.getApprover());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627270Req.class);
        ObjValidater.validateReq(req);
    }

}
