package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805092Req;
import com.bh.mall.dto.res.XN805053Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 设置角色
 * @author: xieyj 
 * @since: 2017年7月16日 下午3:41:47 
 * @history:
 */
public class XN805092 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805092Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doRoleUser(req.getUserId(), req.getRoleCode(), req.getUpdater(),
            req.getRemark());
        return new XN805053Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805092Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getRoleCode(),
            req.getUpdater());
    }
}
