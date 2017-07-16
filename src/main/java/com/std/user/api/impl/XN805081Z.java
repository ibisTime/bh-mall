package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805081ZReq;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 修改用户扩展信息
 * @author: xieyj 
 * @since: 2017年7月16日 下午3:01:50 
 * @history:
 */
public class XN805081Z extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805081ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyUserExt(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805081ZReq.class);
        StringValidater.validateBlank(req.getUserId(), req.getUpdater());
    }
}
