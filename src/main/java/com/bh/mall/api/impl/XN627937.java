package com.bh.mall.api.impl;

import com.bh.mall.ao.IDeliveOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.dto.req.XN627937Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情发货订单
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:30:04 
 * @history:
 */
public class XN627937 extends AProcessor {

    private IDeliveOrderAO deliveOrderAO = SpringContextHolder
        .getBean(IDeliveOrderAO.class);

    private XN627937Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return deliveOrderAO.getDeliveOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627937Req.class);
    }
}
