package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805064Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 修改登录密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:09:51 
 * @history:
 */
public class XN805064 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805064Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyLoginPwd(req.getUserId(), req.getOldLoginPwd(),
            req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805064Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getOldLoginPwd(),
            req.getNewLoginPwd());
    }

}
