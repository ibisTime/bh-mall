package com.bh.mall.api.impl;

import java.util.Date;

import com.bh.mall.ao.IAgencyLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.AgencyLog;
import com.bh.mall.dto.req.XN627360Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询代理轨迹
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627360 extends AProcessor {

    private IAgencyLogAO agencyLogAO = SpringContextHolder
        .getBean(IAgencyLogAO.class);

    private XN627360Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgencyLog condition = new AgencyLog();
        condition.setKeyWord(req.getKeyword());
        condition.setStatus(req.getStatus());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));

        Date approveDatetimeStart = DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1);
        Date approveDatetimeEnd = DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1);
        condition.setApproveDatetimeStart(approveDatetimeStart);
        condition.setApproveDatetimeEnd(approveDatetimeEnd);

        return agencyLogAO.queryAgencyLogList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627360Req.class);
    }

}
