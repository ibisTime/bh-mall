package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627312Req;
import com.bh.mall.dto.res.XN627312Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理端代注册
 * @author: nyc 
 * @since: 2018年4月26日 下午6:14:01 
 * @history:
 */
public class XN627312 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN627312Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627312Res(userAO.addUser(req.getKind(), req.getMobile(),
            req.getLoginPwd(), req.getUserReferee(), req.getFromInfo()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627312Req.class);
        ObjValidater.validateReq(req);
    }

}
