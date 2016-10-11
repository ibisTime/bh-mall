package com.std.user.api.impl;

import com.std.user.ao.IUserAO;
import com.std.user.api.AProcessor;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.dto.req.XN805300Req;
import com.std.user.dto.res.BooleanRes;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 单用户划账
 * @author: xieyj 
 * @since: 2016年10月11日 下午8:45:42 
 * @history:
 */
public class XN805300 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        userAO.doTransfer(req.getUserId(), req.getDirection(), amount,
            req.getRemark(), req.getRefNo());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805300Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getDirection());
        StringValidater.validateAmount(req.getAmount());
    }
}
