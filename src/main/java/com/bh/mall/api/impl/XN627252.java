package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627252Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分配代理
 * @author: nyc 
 * @since: 2018年3月29日 下午6:25:51 
 * @history:
 */
public class XN627252 extends AProcessor {

    private IYxFormAO yxForm = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627252Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        yxForm.allotAgency(req.getUserId(), req.getToUserId(),
            req.getApprover());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627252Req.class);
        ObjValidater.validateReq(req);
    }
}
