package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805151Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 微信登录——判断产生的openId是否已存在；若存在就直接登录，否则需绑定手机号注册
 * @author: xieyj 
 * @since: 2016年11月17日 下午1:02:03 
 * @history:
 */
public class XN805151 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805151Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doLoginWeChat(req.getCode(), req.getMobile(),
            req.getIsRegHx(), req.getCompanyCode(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805151Req.class);
        StringValidater.validateBlank(req.getCode(), req.getCompanyCode(),
            req.getSystemCode());
    }
}
