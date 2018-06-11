package com.bh.mall.api.impl;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627454Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 检查余额是否可以低于红线
 * @author: nyc 
 * @since: 2018年6月11日 下午8:03:48 
 * @history:
 */
public class XN627454 extends AProcessor {

    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN627454Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(accountAO.checkAmount(req.getUserId()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627454Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}