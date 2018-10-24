package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627110Req;
import com.xn.sdhh.dto.res.XN627312Res;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

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
        return new XN627312Res(
            userAO.addSYSUser(req.getMobile(), req.getLoginPwd(),
                req.getRealName(), req.getPhoto(), req.getRemark()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627110Req.class);
        ObjValidater.validateReq(req);
    }
}
