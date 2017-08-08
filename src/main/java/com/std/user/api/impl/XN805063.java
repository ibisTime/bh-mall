package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805063Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 重置登录密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:03:24 
 * @history:
 */
public class XN805063 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805063Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetLoginPwd(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805063Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode());
    }
}
