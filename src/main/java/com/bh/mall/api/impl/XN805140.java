package com.bh.mall.api.impl;

import com.bh.mall.ao.ISignLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805140Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 每日签到
 * @author: xieyj 
 * @since: 2017年9月4日 下午8:39:43 
 * @history:
 */
public class XN805140 extends AProcessor {

    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805140Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return signLogAO.addSignLog(req.getUserId(), req.getLocation());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805140Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
