package com.std.user.api.impl;

import com.std.user.ao.ISignLogAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805140Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 每日签到
 * @author: xieyj 
 * @since: 2017年9月4日 下午8:39:43 
 * @history:
 */
public class XN805140 extends AProcessor {

    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805140Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return signLogAO.addSignLog(req.getUserId(), req.getLocation());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805140Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
