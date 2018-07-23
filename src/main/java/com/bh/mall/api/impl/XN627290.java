package com.bh.mall.api.impl;

import java.util.Date;

import com.bh.mall.ao.IImpowerApplyAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.ImpowerApply;
import com.bh.mall.dto.req.XN627290Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询代理轨迹
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627290 extends AProcessor {

    private IImpowerApplyAO agencyLogAO = SpringContextHolder
        .getBean(IImpowerApplyAO.class);

    private XN627290Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ImpowerApply condition = new ImpowerApply();
        condition.setKeyWord(req.getKeyword());
        condition.setStatus(req.getStatus());
        // condition.setLevel(StringValidater.toInteger(req.getLevel()));

        Date approveDatetimeStart = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date approveDatetimeEnd = DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setApproveDatetimeStart(approveDatetimeStart);
        condition.setApproveDatetimeEnd(approveDatetimeEnd);

        return agencyLogAO.queryImpowerApplyList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627290Req.class);
    }

}
