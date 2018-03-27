package com.bh.mall.api.impl;

import com.bh.mall.ao.IBankcardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN802012Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 修改银行卡
 * @author: xieyj 
 * @since: 2017年1月18日 上午11:08:40 
 * @history:
 */
public class XN802012 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.editBankcard(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802012Req.class);
        StringValidater.validateBlank(req.getCode(), req.getBankcardNumber(),
            req.getBankCode(), req.getBankName());
    }
}
