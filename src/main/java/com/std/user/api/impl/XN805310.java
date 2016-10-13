package com.std.user.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.user.ao.IAJourAO;
import com.std.user.api.AProcessor;
import com.std.user.common.DateUtil;
import com.std.user.common.JsonUtil;
import com.std.user.core.StringValidater;
import com.std.user.domain.AccountJour;
import com.std.user.dto.req.XN805310Req;
import com.std.user.exception.BizException;
import com.std.user.exception.ParaException;
import com.std.user.spring.SpringContextHolder;

/**
 * 分页查询资金明细(front/oss)
 * @author: xieyj 
 * @since: 2016年10月11日 下午8:56:08 
 * @history:
 */
public class XN805310 extends AProcessor {
    private IAJourAO aJourAO = SpringContextHolder.getBean(IAJourAO.class);

    private XN805310Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AccountJour condition = new AccountJour();
        condition.setAccountNumber(req.getUserId());
        condition.setMobileForLikeQuery(req.getMobile());
        condition.setAjNo(StringValidater.toLong(req.getAjNo()));
        condition.setBizType(req.getBizType());

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAJourAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return aJourAO.queryAccountJourPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805310Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
