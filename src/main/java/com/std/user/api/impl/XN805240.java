package com.std.user.api.impl;

import com.std.user.ao.IBlacklistAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805240Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 解除黑名单
 * @author: xieyj 
 * @since: 2017年9月12日 下午4:13:28 
 * @history:
 */
public class XN805240 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805240Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long id = StringValidater.toLong(req.getId());
        blacklistAO.dropBlacklist(id, req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805240Req.class);
        StringValidater.validateBlank(req.getId(), req.getUpdater());
    }
}
