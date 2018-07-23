package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627271Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 授权审核
 * @author: clockorange 
 * @since: Jul 16, 2018 5:23:32 PM 
 * @history:
 */

public class XN627271 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627271Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(userAO.approveImpower(req.getUserId(),
            req.getApprover(), req.getResult(), req.getRemark()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627271Req.class);
        ObjValidater.validateReq(req);
    }
}
