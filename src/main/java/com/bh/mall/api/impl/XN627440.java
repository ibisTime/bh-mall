package com.bh.mall.api.impl;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627440Req;
import com.bh.mall.enums.EBizType;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 可提现账户转云仓
 * @author: nyc 
 * @since: 2018年4月12日 下午1:35:21 
 * @history:
 */
public class XN627440 extends AProcessor {

    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN627440Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        accountAO.transAmountCZB(req.getUserId(), ECurrency.YJ_CNY.getCode(),
            req.getUserId(), ECurrency.MK_CNY.getCode(),
            StringValidater.toLong(req.getAmount()), EBizType.AJ_MKCZ.getCode(),
            "可提现账户转入门槛账户[" + StringValidater.toLong(req.getAmount()) + "]",
            "可提现账户转入门槛账户[" + StringValidater.toLong(req.getAmount()) + "]",
            req.getUserId());
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627440Req.class);
        ObjValidater.validateReq(req);
    }

}
