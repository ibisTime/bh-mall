package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627301Req;
import com.bh.mall.enums.EUserKind;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * B端登录注册
 * @author: myb858 
 * @since: 2015年11月10日 下午12:59:10 
 * @history:
 */
public class XN627301 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN627301Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        System.out.println("userReferee:" + req.getUserReferee());
        return userAO.doLoginWeChatByMerchant(req.getCode(),
            EUserKind.Merchant.getCode(), req.getUserReferee());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627301Req.class);
        ObjValidater.validateReq(req);
    }

}