package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627300Req;
import com.bh.mall.dto.res.XN627300Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 普通登录
 * @author: xieyj 
 * @since: 2016年11月22日 下午3:39:17 
 * @history:
 */
public class XN627300 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN627300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627300Res(userAO.doLogin(req.getLoginName(),
            req.getLoginPwd(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627300Req.class);
        ObjValidater.validateReq(req);
    }
}
