package com.bh.mall.api.impl;

import com.bh.mall.ao.IChannelBankAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627100Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 新增银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN627100 extends AProcessor {

    private IChannelBankAO channelBankAO = SpringContextHolder
        .getBean(IChannelBankAO.class);

    private XN627100Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return channelBankAO.addChannelBank(req.getBankCode(),
            req.getBankName(), req.getUpdater());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627100Req.class);
        ObjValidater.validateReq(req);

    }

}
