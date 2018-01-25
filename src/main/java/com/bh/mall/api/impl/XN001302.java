package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN001302Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 更新用户等级
 * @author: xieyj 
 * @since: 2017年4月1日 下午2:46:16 
 * @history:
 */
public class XN001302 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN001302Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doUpLevel(req.getUserId(), req.getLevel());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN001302Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getLevel());
    }
}
