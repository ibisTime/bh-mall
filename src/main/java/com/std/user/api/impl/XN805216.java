package com.std.user.api.impl;

import com.std.user.ao.IAuthLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805216Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 详情查询认证记录
 * @author: xieyj 
 * @since: 2017年9月10日 上午10:24:10 
 * @history:
 */
public class XN805216 extends AProcessor {
    private IAuthLogAO authLogAO = SpringContextHolder
        .getBean(IAuthLogAO.class);

    private XN805216Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return authLogAO.getAuthLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805216Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
