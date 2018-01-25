package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserRelationAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805116Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 关注人是否存在关联
 * @author: xieyj 
 * @since: 2016年8月31日 上午9:26:10 
 * @history:
 */
public class XN805116 extends AProcessor {
    private IUserRelationAO userRelationAO = SpringContextHolder
        .getBean(IUserRelationAO.class);

    private XN805116Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userRelationAO.isExistUserRelation(req.getUserId(),
            req.getToUser());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805116Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getToUser());
    }
}
