package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.common.PhoneUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805041Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 前端用户普通注册
 * @author: myb858 
 * @since: 2015年8月23日 上午11:42:00
 * @history:
 */
public class XN805041 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805041Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        return userAO.doRegister(req.getMobile(), req.getLoginPwd(),
            req.getUserReferee(), req.getUserRefereeKind(),
            req.getSmsCaptcha(), req.getKind(), req.getIsRegHx(),
            req.getProvince(), req.getCity(), req.getArea(),
            req.getCompanyCode(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805041Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getLoginPwd(),
            req.getSmsCaptcha(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode());
        PhoneUtil.checkMobile(req.getMobile());// 判断格式
    }
}
