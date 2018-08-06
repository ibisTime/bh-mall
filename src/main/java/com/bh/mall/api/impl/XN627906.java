package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627906Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品编号-列表查询
 * @author: LENOVO 
 * @since: 2018年8月2日 下午1:38:42 
 * @history:
 */
public class XN627906 extends AProcessor {
    private IInnerSpecsAO innerSpecsAO = SpringContextHolder
        .getBean(IInnerSpecsAO.class);

    private XN627906Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        InnerSpecs condition = new InnerSpecs();

        condition.setInnerProductCode(req.getInnerProductCode());
        return innerSpecsAO.queryInnerSpecsList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627906Req.class);
        ObjValidater.validateReq(req);
    }
}
