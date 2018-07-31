package com.bh.mall.api.impl;

import com.bh.mall.ao.ISYSUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627110Req;
import com.bh.mall.dto.res.XN627312Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增用户
 * @author: nyc 
 * @since: 2018年4月26日 下午6:14:01 
 * @history:
 */

public class XN627110 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627110Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627312Res(userAO.addSYSUser(req.getMobile(),
            req.getLoginPwd(), req.getRealName(), req.getPhoto()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627110Req.class);
        ObjValidater.validateReq(req);
    }
}
