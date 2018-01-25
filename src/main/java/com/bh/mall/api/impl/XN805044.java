package com.bh.mall.api.impl;

import com.bh.mall.ao.IUserAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN805044ZReq;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 用户审核
 * @author: haiqingzheng 
 * @since: 2017年5月17日 下午1:59:50 
 * @history:
 */
public class XN805044 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805044ZReq req = null;

    /** 
     * @see com.bh.mall.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        userAO.doApproveUser(req.getUserId(), req.getApprover(),
            req.getApproveResult(), req.getDivRate(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.bh.mall.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805044ZReq.class);
        StringValidater.validateBlank(req.getUserId(), req.getApprover(),
            req.getApproveResult());
    }

}
