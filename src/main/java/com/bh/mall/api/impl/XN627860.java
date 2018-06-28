package com.bh.mall.api.impl;

import com.bh.mall.ao.IAwardIntervalAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627860Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增出货奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:34:58 
 * @history:
 */
public class XN627860 extends AProcessor {

    private IAwardIntervalAO awardIntervalAO = SpringContextHolder
        .getBean(IAwardIntervalAO.class);

    private XN627860Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long startAmount = StringValidater.toLong(req.getStartAmount());
        Long endAmount = StringValidater.toLong(req.getEndAmount());
        return awardIntervalAO.addAwardInterval(req.getLevel(), startAmount,
            endAmount, req.getPercent(), req.getUpdater(), req.getRemark());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627860Req.class);
        ObjValidater.validateReq(req);
    }

}
