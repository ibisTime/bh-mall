package com.std.user.api.impl;

import java.util.Map;

import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN807910Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.third.wechat.impl.TokenSingleton;

/**
 * 微信JS-SDK使用权限签名
 * @author: xieyj 
 * @since: 2017年3月29日 下午7:57:16 
 * @history:
 */
public class XN807910 extends AProcessor {

    private XN807910Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TokenSingleton tokenSingleton = TokenSingleton.getInstance();
        Map<String, String> map = tokenSingleton.getMap(req.getSystemCode(),
            req.getCompanyCode());
        System.out.println(map);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN807910Req.class);
        StringValidater
            .validateBlank(req.getSystemCode(), req.getCompanyCode());
    }
}
