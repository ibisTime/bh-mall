package com.std.user.api.impl;

import com.std.user.ao.ISignLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.dto.req.XN805100Req;
import com.std.user.dto.res.PKCodeRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

public class XN805100 extends AProcessor {

    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805100Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(signLogAO.saveSignLog(req.getUserId(),
            req.getLocation()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805100Req.class);
    }

}
