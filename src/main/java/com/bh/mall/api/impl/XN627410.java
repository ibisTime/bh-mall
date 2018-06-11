package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAddressAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.Address;
import com.bh.mall.dto.req.XN627410Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/** 
 * 分页查收件地址
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN627410 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN627410Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Address condition = new Address();
        condition.setUserId(req.getUserId());
        condition.setIsDefault(req.getIsDefault());
        String column = req.getOrderColumn();
        if (StringUtils.isNotBlank(column)) {
            column = IAddressAO.DEFAULT_ORDER_COLUMN;
        }
        return addressAO.queryAddressList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627410Req.class);
        ObjValidater.validateReq(req);
    }
}
