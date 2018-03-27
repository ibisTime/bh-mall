package com.bh.mall.api.impl;

import com.bh.mall.ao.IInnerOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.InnerOrder;
import com.bh.mall.dto.req.XN627732Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询内购订单
 * @author: nyc 
 * @since: 2018年3月27日 下午7:24:18 
 * @history:
 */
public class XN627732 extends AProcessor {

    private IInnerOrderAO innerOrderAO = SpringContextHolder
        .getBean(IInnerOrderAO.class);

    private XN627732Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        InnerOrder condition = new InnerOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setTeamName(req.getTeamName());
        condition.setStatus(req.getStatus());
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));
        return innerOrderAO.queryInnerOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627732Req.class);
    }

}
