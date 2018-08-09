package com.bh.mall.api.impl;

import com.bh.mall.ao.IProCodeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627872Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增防伪溯源码
 * @author: nyc 
 * @since: 2018年7月1日 下午9:22:35 
 * @history:
 */
public class XN627872 extends AProcessor {

    private IProCodeAO proCodeAO = SpringContextHolder
        .getBean(IProCodeAO.class);

    private XN627872Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        proCodeAO.addProCode(StringValidater.toInteger(req.getProNumber()),
            StringValidater.toInteger(req.getMiniNumber()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627872Req.class);
        ObjValidater.validateReq(req);
    }

}
