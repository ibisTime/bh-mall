package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.common.PhoneUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805041Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 前端用户普通注册
 * @author: myb858 
 * @since: 2015年8月23日 上午11:42:00
 * @history:
 */
public class XN805041 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805041Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        return userAO.doRegister(req.getMobile(), req.getLoginPwd(),
            req.getUserReferee(), req.getUserRefereeKind(),
            req.getSmsCaptcha(), req.getKind(), req.getIsRegHx(),
            req.getProvince(), req.getCity(), req.getArea(),
            req.getCompanyCode(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805041Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getLoginPwd(),
            req.getSmsCaptcha(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode());
        PhoneUtil.checkMobile(req.getMobile());// 判断格式
    }
}
