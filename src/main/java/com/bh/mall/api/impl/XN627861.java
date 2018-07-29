package com.bh.mall.api.impl;

import com.bh.mall.ao.IChAwardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627861Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 删除出货奖励
 * @author: nyc 
 * @since: 2018年6月28日 下午5:55:07 
 * @history:
 */
public class XN627861 extends AProcessor {

    private IChAwardAO chAwardAO = SpringContextHolder
        .getBean(IChAwardAO.class);

    private XN627861Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        chAwardAO.dropChAward(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627861Req.class);
        ObjValidater.validateReq(req);
    }

}
