package com.bh.mall.api.impl;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627250Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 申请代理
 * @author: nyc 
 * @since: 2018年6月7日 下午8:45:11 
 * @history:
 */
public class XN627250 extends AProcessor {
    private IBuserAO userAO = SpringContextHolder.getBean(IBuserAO.class);

    private XN627250Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.applyIntent(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627250Req.class);
        ObjValidater.validateReq(req);
    }

}
