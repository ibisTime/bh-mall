package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627268Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 接受意向代理
 * @author: clockorange 
 * @since: Jul 16, 2018 3:30:33 PM 
 * @history:
 */
public class XN627268 extends AProcessor {

    private IYxFormAO userAO = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627268Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.acceptIntention(req.getUserId(), req.getApprover(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627268Req.class);
        ObjValidater.validateReq(req);
    }

}
