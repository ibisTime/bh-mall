package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.DateUtil;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.YxForm;
import com.bh.mall.dto.req.XN627265Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询授权单
 * @author: nyc 
 * @since: 2018年8月5日 下午10:07:46 
 * @history:
 */
public class XN627265 extends AProcessor {

    private IYxFormAO yxFormAO = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627265Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        YxForm condition = new YxForm();
        condition.setApplyLevel(StringValidater.toInteger(req.getApplyLevel()));
        condition.setApprover(req.getApprover());
        condition.setKeyWord(req.getKeyword());
        condition.setToUserId(req.getToUserId());
        condition.setStatus(req.getStart());

        condition.setApplyDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setApplyDatetimeEnd(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IYxFormAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return yxFormAO.queryYxFormPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627265Req.class);
        ObjValidater.validateReq(req);
    }

}
