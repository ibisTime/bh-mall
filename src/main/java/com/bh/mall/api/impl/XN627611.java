package com.bh.mall.api.impl;

import com.bh.mall.ao.ISpecsLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.domain.SpecsLog;
import com.bh.mall.dto.req.XN627611Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询库存记录
 * @author: nyc 
 * @since: 2018年3月26日 下午1:58:20 
 * @history:
 */
public class XN627611 extends AProcessor {

    private ISpecsLogAO productLogAO = SpringContextHolder
        .getBean(ISpecsLogAO.class);

    private XN627611Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SpecsLog condition = new SpecsLog();
        condition.setProductCode(req.getProductCode());
        condition.setType(req.getType());
        condition.setStartDatetime(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        return productLogAO.queryProductLogList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627611Req.class);
    }

}
