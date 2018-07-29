package com.bh.mall.api.impl;

import com.bh.mall.ao.IWareLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.domain.WareLog;
import com.bh.mall.dto.req.XN627832Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *列表查流水  
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627832 extends AProcessor {
    private IWareLogAO wareLogAO = SpringContextHolder
        .getBean(IWareLogAO.class);

    private XN627832Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WareLog condition = new WareLog();
        condition.setKeyword(req.getKeyword());
        condition.setStatus(req.getStatus());
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        return wareLogAO.queryWareLogList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627832Req.class);
        ObjValidater.validateReq(req);

    }
}
