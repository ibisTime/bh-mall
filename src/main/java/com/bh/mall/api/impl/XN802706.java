package com.bh.mall.api.impl;

import com.bh.mall.ao.IChargeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN802706Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询充值订单
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:58:10 
 * @history:
 */
public class XN802706 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802706Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return chargeAO.getCharge(req.getCode(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802706Req.class);
        StringValidater.validateBlank(req.getCode(), req.getSystemCode());

    }

}
