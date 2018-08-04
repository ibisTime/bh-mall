package com.bh.mall.api.impl;

import com.bh.mall.ao.IAddressAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.Address;
import com.bh.mall.dto.req.XN627400Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 收件地址新增
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN627400 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN627400Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Address data = new Address();
        data.setUserId(req.getUserId());
        data.setReceiver(req.getReceiver());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setIsDefault(req.getIsDefault());
        return new PKCodeRes(addressAO.addAddress(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627400Req.class);
        ObjValidater.validateReq(req);
    }
}
