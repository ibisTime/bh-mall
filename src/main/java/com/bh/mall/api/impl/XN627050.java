package com.bh.mall.api.impl;

import com.bh.mall.ao.IAddressAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Address;
import com.bh.mall.dto.req.XN627050Req;
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
public class XN627050 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN627050Req req = null;

    /**
     * @see com.xnjr.mall.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Address data = new Address();
        data.setUserId(req.getUserId());
        data.setReceiver(req.getReceiver());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setDistrict(req.getDistrict());
        data.setDetailAddress(req.getDetailAddress());
        data.setIsDefault(req.getIsDefault());
        return new PKCodeRes(addressAO.addAddress(data));
    }

    /**
     * @see com.xnjr.mall.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627050Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getReceiver(),
            req.getMobile(), req.getProvince(), req.getCity(),
            req.getDistrict(), req.getDetailAddress());
    }
}
