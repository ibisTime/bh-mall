package com.std.user.api.impl;

import com.std.user.ao.IAuthLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805210Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 学信网学生手动认证
 * @author: xieyj 
 * @since: 2017年9月10日 下午12:54:49 
 * @history:
 */
public class XN805210 extends AProcessor {
    private IAuthLogAO authLogAO = SpringContextHolder
        .getBean(IAuthLogAO.class);

    private XN805210Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        authLogAO.applyStudentAuth(req.getXuexinPic(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805210Req.class);
        StringValidater.validateBlank(req.getXuexinPic(), req.getUserId());
    }
}
