package com.bh.mall.api.impl;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627805Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询余额红线
 * @author: nyc 
 * @since: 2018年4月23日 上午10:45:56 
 * @history:
 */
public class XN627805 extends AProcessor {

    private IChangeProductAO changeProductAO = SpringContextHolder
        .getBean(IChangeProductAO.class);

    private XN627805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return changeProductAO.checkAmount(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627805Req.class);
        ObjValidater.validateReq(req);

    }

}
