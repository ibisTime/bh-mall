package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627112Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *  重置手机 （系统用户）
 * @author: clockorange 
 * @since: Jul 17, 2018 11:54:25 AM 
 * @history:
 */

public class XN627112 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627112Req req = null;


    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetMoblie(req.getUserId(), req.getKind(), req.getNewMobile(),
            req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627112Req.class);
        ObjValidater.validateReq(req);
    }

}
