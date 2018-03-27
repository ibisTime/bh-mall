package com.bh.mall.api.impl;

import com.bh.mall.ao.IBankcardAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627520Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 绑定银行卡
 * @author: asus 
 * @since: 2016年12月22日 下午5:04:53 
 * @history:
 */
public class XN627520 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN627520Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.addBankcard(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627520Req.class);
        ObjValidater.validateReq(req);
    }
}
