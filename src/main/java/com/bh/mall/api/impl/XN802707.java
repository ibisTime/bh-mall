package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IChargeAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.Charge;
import com.bh.mall.dto.req.XN802707Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询充值订单
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:58:26 
 * @history:
 */
public class XN802707 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802707Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge condition = new Charge();
        condition.setPayGroup(req.getPayGroup());
        condition.setRefNo(req.getRefNo());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountName(req.getAccountName());

        condition.setPayAccountNumber(req.getPayAccountNumber());
        condition.setPayAccountName(req.getPayAccountName());
        condition.setType(req.getType());
        condition.setCurrency(req.getCurrency());
        condition.setBizType(req.getBizType());
        condition.setPayCardNo(req.getPayCardNo());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());

        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(
            req.getApplyDateEnd(), true));
        condition.setPayUser(req.getPayUser());
        condition.setPayDatetimeStart(DateUtil.getFrontDate(
            req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(DateUtil.getFrontDate(req.getPayDateEnd(),
            true));

        condition.setChannelType(req.getChannelType());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IChargeAO.DEFAULT_ORDER_COLUMN;
        }

        condition.setOrder(orderColumn, req.getOrderDir());
        return chargeAO.queryChargeList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802707Req.class);
    }
}
