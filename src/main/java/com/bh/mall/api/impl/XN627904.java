package com.bh.mall.api.impl;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627904Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 审核取消
 * @author: nyc 
 * @since: 2018年8月7日 下午9:40:02 
 * @history:
 */
public class XN627904 extends AProcessor {

    private IInOrderAO inOrderAO = SpringContextHolder
        .getBean(IInOrderAO.class);

    private XN627904Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        inOrderAO.approveCancel(req.getCode(), req.getResult(),
            req.getApprover(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627904Req.class);
        ObjValidater.validateReq(req);
    }

}
