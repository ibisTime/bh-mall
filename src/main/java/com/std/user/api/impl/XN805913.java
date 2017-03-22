package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805913Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 查询用户详情
 * @author: xieyj 
 * @since: 2017年3月6日 上午11:49:24 
 * @history:
 */
public class XN805913 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805913Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doGetDetailUser(req.getUserId(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805913Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getSystemCode());
    }
}
