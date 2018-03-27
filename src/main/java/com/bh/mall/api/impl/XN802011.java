package com.bh.mall.api.impl;

import com.bh.mall.ao.IBankcardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN802011Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 删除银行卡
 * @author: asus 
 * @since: 2016年12月22日 下午5:54:08 
 * @history:
 */
public class XN802011 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802011Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.dropBankcard(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802011Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
