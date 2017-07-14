package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805043Req;
import com.std.user.dto.res.XN805042Res;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 申请注册
 * @author: xieyj 
 * @since: 2017年7月14日 下午11:03:23 
 * @history:
 */
public class XN805043 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805043Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805042Res(userAO.doApplyRegUser(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805043Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
    }
}
