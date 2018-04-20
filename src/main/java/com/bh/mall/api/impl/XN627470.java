package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IChargeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Charge;
import com.bh.mall.dto.req.XN627470Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 充值订单分页查询
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:27:55 
 * @history:
 */
public class XN627470 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN627470Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge condition = new Charge();
        condition.setPayGroup(req.getPayGroup());
        condition.setRefNo(req.getRefNo());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountName(req.getAccountName());

        condition.setType(req.getType());
        condition.setCurrency(req.getCurrency());
        condition.setBizType(req.getBizType());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());

        condition.setApplyDatetimeStart(
            DateUtil.getFrontDate(req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(
            DateUtil.getFrontDate(req.getApplyDateEnd(), true));
        condition.setPayUser(req.getPayUser());
        condition.setPayDatetimeStart(
            DateUtil.getFrontDate(req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(
            DateUtil.getFrontDate(req.getPayDateEnd(), true));

        condition.setChannelType(req.getChannelType());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IChargeAO.DEFAULT_ORDER_COLUMN;
        }

        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return chargeAO.queryChargePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627470Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
