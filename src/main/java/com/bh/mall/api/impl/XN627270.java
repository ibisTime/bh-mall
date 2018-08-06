package com.bh.mall.api.impl;

import com.bh.mall.ao.ISqFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627270Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理申请（包含推荐人）
 * @author: nyc 
 * @since: 2018年3月29日 下午5:16:38 
 * @history:
 */
public class XN627270 extends AProcessor {
    private ISqFormAO userAO = SpringContextHolder.getBean(ISqFormAO.class);

    private XN627270Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.applyHaveUserReferee(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627270Req.class);
        ObjValidater.validateReq(req);
    }

}
