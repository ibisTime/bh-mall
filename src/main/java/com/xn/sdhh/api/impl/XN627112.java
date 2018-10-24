package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627112Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

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
