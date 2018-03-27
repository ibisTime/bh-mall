package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627720Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 提交内购产品订单
 * @author: nyc 
 * @since: 2018年3月27日 下午3:05:04 
 * @history:
 */
public class XN627720 extends AProcessor {

    private IInnerOrderAO innerOrderAO = SpringContextHolder
        .getBean(IInnerOrderAO.class);

    private XN627720Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return innerOrderAO.addInnerOrder(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627720Req.class);
        ObjValidater.validateReq(req);
    }

}
