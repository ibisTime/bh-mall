package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627113Req;
import com.xn.sdhh.dto.res.BooleanRes;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 管理员重置登录密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:26:45 
 * @history:
 */
public class XN627113 extends AProcessor {
    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627113Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.resetAdminLoginPwd(req.getUserId(), req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627113Req.class);
        ObjValidater.validateReq(req);
    }

}
