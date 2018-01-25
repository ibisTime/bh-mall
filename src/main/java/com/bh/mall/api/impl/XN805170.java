package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805170Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 微信登录
 * 1、首次登录，输入手机号，短信验证码；
 * 2、之前用户首次登录，输入手机号进行绑定
 * 3、二次登录直接登录
 * @author: xieyj 
 * @since: 2017年4月25日 下午4:40:40 
 * @history:
 */
public class XN805170 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805170Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doLoginWeChat(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805170Req.class);
        StringValidater.validateBlank(req.getCode(), req.getType(),
            req.getCompanyCode(), req.getSystemCode());
    }
}
