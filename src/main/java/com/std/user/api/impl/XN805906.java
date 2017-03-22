package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805906Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 根据用户编号和密码判断登录密码是否正确
 * @author: xieyj 
 * @since: 2017年1月10日 下午8:29:26 
 * @history:
 */
public class XN805906 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805906Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCheckLoginPwd(req.getUserId(), req.getLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805906Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getLoginPwd(),
            req.getTokenId());
    }
}
