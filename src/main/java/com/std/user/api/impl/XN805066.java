package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805066Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 设置支付密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:41:15 
 * @history:
 */
public class XN805066 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805066Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doSetTradePwd(req.getUserId(), req.getTradePwd(),
            req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805066Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getTradePwd(),
            req.getSmsCaptcha());
    }

}
