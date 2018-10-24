package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627119Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 修改照片
 * @author: clockorange 
 * @since: Aug 6, 2018 2:26:53 PM 
 * @history:
 */

public class XN627119 extends AProcessor {
    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN627119Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysUserAO.doModifyPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627119Req.class);
        ObjValidater.validateReq(req);
    }

}
