package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805095Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 修改用户信息
 * @author: xieyj 
 * @since: 2017年7月16日 下午4:35:34 
 * @history:
 */
public class XN805095 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805095Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyUser(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805095Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getUpdater());
    }
}
