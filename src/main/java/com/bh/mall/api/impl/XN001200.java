package com.bh.mall.api.impl;

import com.bh.mall.ao.ISmsOutAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN001200Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 业务biz委托发送短信
 * @author: xieyj 
 * @since: 2017年4月10日 下午9:10:07 
 * @history:
 */
public class XN001200 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN001200Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendContent(req.getTokenId(), req.getUserId(),
            req.getContent());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN001200Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId(),
            req.getContent());
    }
}
