package com.bh.mall.api.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IAccountAO;
import com.bh.mall.ao.IWithdrawAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Account;
import com.bh.mall.domain.Withdraw;
import com.bh.mall.dto.req.XN627513Req;
import com.bh.mall.enums.ECurrency;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询取现订单（front）
 * @author: xieyj 
 * @since: 2017年5月17日 下午5:17:47 
 * @history:
 */
public class XN627513 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN627513Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Withdraw condition = new Withdraw();
        if (StringUtils.isNotBlank(req.getUserId())) {
            try {
                List<Account> accounts = accountAO.getAccountByUserId(
                    req.getUserId(), ECurrency.TX_CNY.getCode());
                if (accounts.size() > 0) {
                    condition
                        .setAccountNumber(accounts.get(0).getAccountNumber());
                }
            } catch (Exception e) {
            }
        }
        condition.setCode(req.getCode());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getUserId());

        condition.setApplyDatetimeStart(
            DateUtil.getFrontDate(req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(
            DateUtil.getFrontDate(req.getApplyDateEnd(), true));
        condition.setApproveDatetimeStart(
            DateUtil.getFrontDate(req.getApproveDateStart(), false));
        condition.setApproveDatetimeEnd(
            DateUtil.getFrontDate(req.getApproveDateEnd(), true));
        condition.setPayDatetimeStart(
            DateUtil.getFrontDate(req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(
            DateUtil.getFrontDate(req.getPayDateEnd(), true));

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IWithdrawAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return withdrawAO.queryWithdrawPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627513Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
