package com.bh.mall.api.impl;

import com.bh.mall.ao.IJourAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627495Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 我的流水查询
 * @author: xieyj 
 * @since: 2016年12月24日 上午7:59:19 
 * @history:
 */
public class XN627495 extends AProcessor {
    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN627495Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return jourAO.getJour(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627495Req.class);
        ObjValidater.validateReq(req);
    }
}
