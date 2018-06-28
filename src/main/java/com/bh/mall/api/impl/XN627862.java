package com.bh.mall.api.impl;

import com.bh.mall.ao.IAwardIntervalAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627862Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改介绍奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:55:07 
 * @history:
 */
public class XN627862 extends AProcessor {

    private IAwardIntervalAO awardIntervalAO = SpringContextHolder
        .getBean(IAwardIntervalAO.class);

    private XN627862Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        awardIntervalAO.editAwardInterval(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627862Req.class);
        ObjValidater.validateReq(req);
    }

}
