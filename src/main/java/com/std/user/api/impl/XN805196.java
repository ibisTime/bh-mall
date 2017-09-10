package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805196Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 查询芝麻分
 * @author: xieyj 
 * @since: 2017年9月10日 上午10:24:10 
 * @history:
 */
public class XN805196 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805196Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doZhimaScoreQuery(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805196Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
