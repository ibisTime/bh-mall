package com.bh.mall.api.impl;

import com.bh.mall.ao.IMaterialAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627420Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增素材
 * @author: nyc 
 * @since: 2018年1月31日 下午5:16:16 
 * @history:
 */
public class XN627420 extends AProcessor {

    private IMaterialAO materialAO = SpringContextHolder
        .getBean(IMaterialAO.class);

    private XN627420Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return materialAO.addMaterial(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627420Req.class);
        ObjValidater.validateReq(req);
    }

}
