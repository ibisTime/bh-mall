package com.bh.mall.api.impl;

import com.bh.mall.ao.IWareAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627806Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 查询余额红线
 * @author: nyc 
 * @since: 2018年4月23日 上午10:45:56 
 * @history:
 */
public class XN627806 extends AProcessor {

    private IWareAO wareAO = SpringContextHolder.getBean(IWareAO.class);

    private XN627806Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        wareAO.editWare(req.getCode(), req.getQuantity(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627806Req.class);
        ObjValidater.validateReq(req);

    }

}
