package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN001000Req;
import com.bh.mall.dto.res.XN001000Res;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 拿tokenId的一步，在登录阶段调用
 * @author: myb858 
 * @since: 2015年10月28日 上午10:31:58 
 * @history:
 */
public class XN001000 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN001000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = userAO.doLogin(req.getLoginName(), req.getLoginPwd(),
            null, null, req.getSystemCode());
        XN001000Res res = new XN001000Res();
        res.setTokenId(userId);
        res.setUserId(userId);
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN001000Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getLoginPwd(),
            req.getSystemCode());
    }
}
