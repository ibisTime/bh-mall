package com.bh.mall.api.impl;

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627284Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 注销 / 激活用户 （c端）
 * @author: clockorange 
 * @since: Jul 17, 2018 12:02:47 PM 
 * @history:
 */
public class XN627284 extends AProcessor {

    private ICUserAO userAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN627284Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCloseOpen(req.getUserId(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627284Req.class);
        ObjValidater.validateReq(req);
    }

}
