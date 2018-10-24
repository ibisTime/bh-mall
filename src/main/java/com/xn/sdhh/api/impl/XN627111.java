package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.ISYSUserAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627111Req;
import com.xn.sdhh.dto.res.XN627111Res;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 普通登录
 * @author: xieyj 
 * @since: 2016年11月22日 下午3:39:17 
 * @history:
 */
public class XN627111 extends AProcessor {
    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN627111Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627111Res(
            userAO.doLogin(req.getLoginName(), req.getLoginPwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627111Req.class);
        ObjValidater.validateReq(req);
    }
}
