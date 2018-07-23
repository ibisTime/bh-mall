package com.bh.mall.api.impl;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627255Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * update information
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627255 extends AProcessor {

    private IBuserAO userAO = SpringContextHolder.getBean(IBuserAO.class);

    private XN627255Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.updateInformation(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627255Req.class);
        ObjValidater.validateReq(req);
    }

}
