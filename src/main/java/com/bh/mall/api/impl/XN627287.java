package com.bh.mall.api.impl;

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627287Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * Reset mobile - c user
 * @author: clockorange 
 * @since: Jul 17, 2018 1:07:35 PM 
 * @history:
 */
public class XN627287 extends AProcessor {

    private ICUserAO userAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN627287Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetMoblie(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627287Req.class);
        ObjValidater.validateReq(req);
    }

}
