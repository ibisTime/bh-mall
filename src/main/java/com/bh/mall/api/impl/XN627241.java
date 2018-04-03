package com.bh.mall.api.impl;

import com.bh.mall.ao.IIntroAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627241Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 
 * @author: chenshan 
 * @since: 2018年4月3日 下午7:25:58 
 * @history:
 */
public class XN627241 extends AProcessor {
    private IIntroAO introAO = SpringContextHolder.getBean(IIntroAO.class);

    private XN627241Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        introAO.editIntro(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627241Req.class);
        ObjValidater.validateReq(req);
    }

}
