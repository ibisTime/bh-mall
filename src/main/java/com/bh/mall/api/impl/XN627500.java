package com.bh.mall.api.impl;

import com.bh.mall.ao.IWithdrawAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627500Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 线下取现申请(需交易密码)
 * @author: myb858 
 * @since: 2017年4月24日 下午8:00:31 
 * @history:
 */
public class XN627500 extends AProcessor {

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN627500Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        synchronized (withdrawAO) {
            Long amount = StringValidater.toLong(req.getAmount());
            String code = withdrawAO.applyOrderTradePwd(req.getAccountNumber(),
                amount, req.getPayCardInfo(), req.getPayCardNo(),
                req.getApplyUser(), req.getApplyNote(), req.getTradePwd());
            return new PKCodeRes(code);
        }
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627500Req.class);
        ObjValidater.validateReq(req);
    }
}
