package com.bh.mall.api.impl;

import com.bh.mall.ao.IAddressAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Address;
import com.bh.mall.dto.req.XN627057Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 收件地址详情
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN627057 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN627057Req req = null;

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Address condition = new Address();
        condition.setCode(req.getCode());
        return addressAO.getAddress(req.getCode());
    }

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627057Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
