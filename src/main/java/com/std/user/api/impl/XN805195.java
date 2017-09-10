package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805195Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * h5授权页面返回授权访问URL
 * @author: xieyj 
 * @since: 2016年11月22日 下午3:15:00 
 * @history:
 */
public class XN805195 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805195Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doZhimaH5URLQuery(req.getUserId(), req.getIdNo(),
            req.getRealName());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805195Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getIdNo(),
            req.getRealName());
    }
}
