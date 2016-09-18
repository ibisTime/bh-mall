package com.std.user.api.impl;

import com.std.user.ao.IUserExtAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.dto.req.XN805073Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

public class XN805073 extends AProcessor {

    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN805073Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userExtAO.refreshUserExtPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805073Req.class);
    }

}
