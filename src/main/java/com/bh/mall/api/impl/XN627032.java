package com.bh.mall.api.impl;

import com.bh.mall.ao.IMaterialAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627032Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 删除素材
 * @author: nyc 
 * @since: 2018年2月1日 下午6:21:26 
 * @history:
 */
public class XN627032 extends AProcessor {

    private IMaterialAO materialAO = SpringContextHolder
        .getBean(IMaterialAO.class);

    private XN627032Req req;

    @Override
    public Object doBusiness() throws BizException {
        materialAO.dropMaterial(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627032Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
