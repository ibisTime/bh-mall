package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627902Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品规格-删除
 * @author: LENOVO 
 * @since: 2018年8月1日 下午9:49:11 
 * @history:
 */
public class XN627902 extends AProcessor {
    private IInnerSpecsAO innerSpecsAO = SpringContextHolder
        .getBean(IInnerSpecsAO.class);

    private XN627902Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(innerSpecsAO.dropInnerSpecs(req.getCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627902Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
