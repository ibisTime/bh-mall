package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805092Req;
import com.std.user.dto.res.XN805053Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 设置角色
 * @author: xieyj 
 * @since: 2017年7月16日 下午3:41:47 
 * @history:
 */
public class XN805092 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805092Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doRoleUser(req.getUserId(), req.getRoleCode(), req.getUpdater(),
            req.getRemark());
        return new XN805053Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805092Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getRoleCode(),
            req.getUpdater());
    }
}
