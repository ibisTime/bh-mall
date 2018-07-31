package com.bh.mall.api.impl;

import com.bh.mall.ao.IExchangeOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627790Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 插叙置换总价等
 * @author: nyc 
 * @since: 2018年4月23日 上午10:45:56 
 * @history:
 */
public class XN627804 extends AProcessor {

    private IExchangeOrderAO exchangeOrderAO = SpringContextHolder
        .getBean(IExchangeOrderAO.class);

    private XN627790Req req = null;

    @Override
    public Object doBusiness() throws BizException {
<<<<<<< HEAD
        return exchangeOrderAO.getExchangeOrderMessage(req);
=======
        return exchangeOrderAO.getChangeOrderMessage(req);
>>>>>>> refs/remotes/origin/master
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627790Req.class);
        ObjValidater.validateReq(req);

    }

}
