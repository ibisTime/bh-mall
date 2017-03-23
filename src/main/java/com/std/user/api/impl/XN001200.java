package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN001200Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 应用委托发送短信（因为应用本身只能拿到登录名，其他信息如手机号理论上拿不到的）
 * @author: myb858 
 * @since: 2015年11月10日 上午9:18:50 
 * @history:
 */
public class XN001200 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN001200Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.sendAppSms(req.getUserId(), req.getContent());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN001200Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId(),
            req.getContent());

    }

}
