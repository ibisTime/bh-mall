package com.bh.mall.api.impl;

import com.bh.mall.ao.IBuserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627281Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

public class XN627281 extends AProcessor {
    private IBuserAO userAO = SpringContextHolder.getBean(IBuserAO.class);

    private XN627281Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.resetLoginPwd(req.getUserId(), req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627281Req.class);
        ObjValidater.validateReq(req);
    }

}
