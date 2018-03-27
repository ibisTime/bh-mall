package com.bh.mall.api.impl;

import com.bh.mall.ao.IBankcardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN802017Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询银行卡
 * @author: asus 
 * @since: 2016年12月22日 下午8:10:28 
 * @history:
 */
public class XN802017 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802017Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.getBankcard(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802017Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
