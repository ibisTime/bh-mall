package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805081Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 修改用户姓名和地址信息
 * @author: xieyj 
 * @since: 2017年7月16日 下午3:01:50 
 * @history:
 */
public class XN805081 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805081Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyNameAddress(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805081Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getUpdater());
    }
}
