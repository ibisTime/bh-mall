package com.std.user.api.impl;

import com.std.user.ao.IBlacklistAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805246Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 详情查询黑名单
 * @author: xieyj 
 * @since: 2017年3月6日 上午11:02:02 
 * @history:
 */
public class XN805246 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805246Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long id = StringValidater.toLong(req.getId());
        return blacklistAO.getBlacklist(id);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805246Req.class);
        StringValidater.validateBlank(req.getId());
    }
}
