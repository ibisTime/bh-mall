package com.bh.mall.api.impl;

import com.bh.mall.ao.IMaterialAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627432Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.http.JsonUtils;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 素材详情
 * @author: nyc 
 * @since: 2018年2月1日 下午1:47:36 
 * @history:
 */
public class XN627432 extends AProcessor {

    private IMaterialAO materialAO = SpringContextHolder
        .getBean(IMaterialAO.class);

    private XN627432Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return materialAO.getMaterial(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN627432Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
