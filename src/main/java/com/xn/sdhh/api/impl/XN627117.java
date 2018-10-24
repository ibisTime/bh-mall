package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627117Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 设置角色
 * @author: nyc 
 * @since: 2018年4月26日 下午6:14:01 
 * @history:
 */
public class XN627117 extends AProcessor {
    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627117Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doRoleSYSUser(req.getUserId(), req.getRoleCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627117Req.class);
        ObjValidater.validateReq(req);
    }

}
