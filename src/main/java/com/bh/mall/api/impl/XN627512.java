package com.bh.mall.api.impl;

import com.bh.mall.ao.IWithdrawAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627512Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 取现详情
 * @author: xieyj 
 * @since: 2017年5月17日 下午6:35:28 
 * @history:
 */
public class XN627512 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN627512Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return withdrawAO.getWithdraw(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627512Req.class);
        ObjValidater.validateReq(req);
    }

}
