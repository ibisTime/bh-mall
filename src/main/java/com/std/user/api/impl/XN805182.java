package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805182Req;
import com.std.user.dto.res.XN805182Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 微信小程序登录——已注册，直接返回userId，未注册先落地，再返回userId
 * @author: xieyj 
 * @since: 2017年1月19日 下午4:48:18 
 * @history:
 */
public class XN805182 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805182Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805182Res(userAO.doLoginSmallWeChat(req.getJsCode(),
            req.getNickname(), req.getSex(), req.getHeadimgurl(),
            req.getCompanyCode(), req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805182Req.class);
        StringValidater.validateBlank(req.getJsCode(), req.getCompanyCode(),
            req.getSystemCode());
    }
}
