package com.bh.mall.api.impl;

import com.bh.mall.ao.ICUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627315Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 设置支付密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:36:19 
 * @history:
 */
public class XN627315 extends AProcessor {
    private ICUserAO userAO = SpringContextHolder.getBean(ICUserAO.class);

    private XN627315Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.getCuser(req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627315Req.class);
        ObjValidater.validateReq(req);
    }

}
