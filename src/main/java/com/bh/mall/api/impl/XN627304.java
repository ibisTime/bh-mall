package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627304Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 管理员重置登录密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:26:45 
 * @history:
 */
public class XN627304 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN627304Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.resetAdminLoginPwd(req.getUserId(), req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627304Req.class);
        ObjValidater.validateReq(req);
    }

}
