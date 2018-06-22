package com.bh.mall.api.impl;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627454Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 充值/扣款
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
        accountAO.transAmount(req.getAccountNumber(), req.getChangeAmount(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627454Req.class);
        ObjValidater.validateReq(req);
    }
}
