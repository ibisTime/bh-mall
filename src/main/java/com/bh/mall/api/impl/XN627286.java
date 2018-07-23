package com.bh.mall.api.impl;

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627286Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * C -user modify photo
 * @author: clockorange 
 * @since: Jul 17, 2018 1:14:37 PM 
 * @history:
 */

public class XN627286 extends AProcessor {

    private ICUserAO userAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN627286Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627286Req.class);
        ObjValidater.validateReq(req);
    }

}
