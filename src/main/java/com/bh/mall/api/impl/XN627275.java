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
 * 通过取消申请授权
 * @author: clockorange 
 * @since: Jul 17, 2018 9:53:55 AM 
 * @history:
 */

public class XN627275 extends AProcessor {

    private ISqFormAO userAO = SpringContextHolder.getBean(ISqFormAO.class);

    private XN627275Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.approveCanenlSqForm(req.getUserId(), req.getApprover(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627275Req.class);
        ObjValidater.validateReq(req);
    }

}
