package com.bh.mall.api.impl;

import com.bh.mall.ao.IAfterSaleAO;
/**
 * 申请售后
 * @author: nyc 
 * @since: 2018年3月28日 下午9:02:41 
 * @history:
 */
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627680Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 申请售后
 * @author: nyc 
 * @since: 2018年3月29日 上午10:53:13 
 * @history:
 */
public class XN627680 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN627680Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(afterSaleAO.addAfterSale(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627680Req.class);
        ObjValidater.validateReq(req);
    }

}
