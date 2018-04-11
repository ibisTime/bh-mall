package com.bh.mall.api.impl;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.dto.req.XN627803Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *详情查询云仓
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627803 extends AProcessor {
    private IChangeProductAO changeProductAO = SpringContextHolder
        .getBean(IChangeProductAO.class);

    private XN627803Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return changeProductAO.getChangeProduct(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627803Req.class);

    }
}
