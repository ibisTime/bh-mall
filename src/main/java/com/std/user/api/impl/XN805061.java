package com.std.user.api.impl;

import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805061Req;
import com.std.user.dto.res.XN805061Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;

/**
 * @author: xieyj 
 * @since: 2016年12月27日 下午9:01:00 
 * @history:
 */
public class XN805061 extends AProcessor {
    private XN805061Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805061Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805061Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
