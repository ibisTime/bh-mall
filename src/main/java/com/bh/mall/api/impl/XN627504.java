package com.bh.mall.api.impl;

import com.bh.mall.ao.IWithdrawAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627504Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 取现回录
 * @author: myb858 
 * @since: 2017年4月24日 下午8:00:31 
 * @history:
 */
public class XN627504 extends AProcessor {

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN627504Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        String code = withdrawAO.backRecord(req.getAccountNumber(), amount,
            req.getPayDatetime(), req.getPayCardInfo(), req.getPayCardNo(),
            req.getApplyUser(), req.getApplyNote());
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627504Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getPayCardInfo(), req.getPayCardNo(), req.getPayDatetime(),
            req.getApplyUser());
        StringValidater.validateAmount(req.getAmount());
    }
}
