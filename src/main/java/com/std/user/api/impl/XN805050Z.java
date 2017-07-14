package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805050ZReq;
import com.std.user.dto.res.XN805043Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 普通登录
 * @author: xieyj 
 * @since: 2016年11月22日 下午3:39:17 
 * @history:
 */
public class XN805050Z extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805050ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805043Res(userAO.doLogin(req.getLoginName(),
            req.getLoginPwd(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805050ZReq.class);
        StringValidater.validateBlank(req.getLoginName(), req.getLoginPwd(),
            req.getKind(), req.getSystemCode());
    }
}
