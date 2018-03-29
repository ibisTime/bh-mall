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
import com.bh.mall.dto.req.XN627681Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 审核售后
 * @author: nyc 
 * @since: 2018年3月29日 上午10:52:56 
 * @history:
 */
public class XN627681 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN627681Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        afterSaleAO.approvrAfterSale(req.getCode(), req.getApprover(),
            req.getApproveNote(), req.getResult());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627681Req.class);
        ObjValidater.validateReq(req);
    }

}
