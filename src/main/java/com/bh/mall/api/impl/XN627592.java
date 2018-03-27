package com.bh.mall.api.impl;

import com.bh.mall.ao.IAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627592Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询奖励
 * @author: nyc 
 * @since: 2018年3月26日 上午11:10:46 
 * @history:
 */
public class XN627592 extends AProcessor {
    private IAwardAO awardAO = SpringContextHolder.getBean(IAwardAO.class);

    private XN627592Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return awardAO.getAward(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627592Req.class);
        ObjValidater.validateReq(req);

    }

}
