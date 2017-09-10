package com.std.user.api.impl;

import com.std.user.ao.IAuthLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805218Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 根据用户编号查询认证记录
 * @author: xieyj 
 * @since: 2017年9月10日 上午10:24:10 
 * @history:
 */
public class XN805218 extends AProcessor {
    private IAuthLogAO authLogAO = SpringContextHolder
        .getBean(IAuthLogAO.class);

    private XN805218Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return authLogAO.getAuthLog(req.getUserId(), req.getType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805218Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getType());
    }
}
