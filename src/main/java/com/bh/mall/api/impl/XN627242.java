package com.bh.mall.api.impl;

import com.bh.mall.ao.IJsAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627242Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 删除介绍奖
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:25:58 
 * @history:
 */
public class XN627242 extends AProcessor {
    private IJsAwardAO jsAwardAO = SpringContextHolder
        .getBean(IJsAwardAO.class);

    private XN627242Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        jsAwardAO.dropJsAward(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627242Req.class);
        ObjValidater.validateReq(req);
    }

}
