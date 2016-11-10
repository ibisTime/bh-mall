package com.std.user.api.impl;

import com.std.user.ao.ISmsOutAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805905Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 发送短信内容
 * @author: xieyj 
 * @since: 2016年10月17日 下午7:23:10 
 * @history:
 */
public class XN805905 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN805905Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendContent(req.getTokenId(), req.getOwnerId(),
            req.getContent());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805905Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getOwnerId(),
            req.getContent());
    }
}
