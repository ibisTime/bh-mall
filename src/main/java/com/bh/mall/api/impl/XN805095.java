package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805095Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改用户信息
 * @author: xieyj 
 * @since: 2017年7月16日 下午4:35:34 
 * @history:
 */
public class XN805095 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805095Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyUser(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805095Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getUpdater());
    }
}
