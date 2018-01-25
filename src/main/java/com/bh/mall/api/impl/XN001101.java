package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN001101Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 校验登录密码正确与否
 * @author: xieyj 
 * @since: 2017年1月10日 下午8:29:26 
 * @history:
 */
public class XN001101 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN001101Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCheckLoginPwd(req.getUserId(), req.getLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN001101Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getLoginPwd(),
            req.getTokenId());
    }
}
