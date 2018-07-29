package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627309Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 重置登录密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:36:19 
 * @history:
 */
public class XN627309 extends AProcessor {

    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN627309Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysUserAO.resetLoginPwd(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627309Req.class);
        ObjValidater.validateReq(req);
    }

}
