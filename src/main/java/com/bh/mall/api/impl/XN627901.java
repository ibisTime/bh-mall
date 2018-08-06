package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627901Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品规格-修改
 * @author: LENOVO 
 * @since: 2018年8月1日 下午10:27:42 
 * @history:
 */
public class XN627901 extends AProcessor {
    private IInnerSpecsAO innerSpecsAO = SpringContextHolder
        .getBean(IInnerSpecsAO.class);

    private XN627901Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        InnerSpecs data = new InnerSpecs();

        data.setCode(req.getCode());
        data.setInnerProductCode(req.getInnerProductCode());
        data.setName(req.getName());
        data.setNumber(Integer.valueOf(req.getNumber()));
        data.setWeight(Integer.valueOf(req.getWeight()));

        data.setPrice(Integer.valueOf(req.getWeight()));
        // data.setRefCode(req.getRefCode());
        data.setIsSingle(req.getIsSingle());
        data.setSingleNumber(Integer.valueOf(req.getSingleNumber()));

        return new BooleanRes(innerSpecsAO.editInnerSpecs(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627901Req.class);
        ObjValidater.validateReq(req);
    }
}
