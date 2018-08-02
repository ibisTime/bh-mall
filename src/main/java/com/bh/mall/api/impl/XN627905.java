package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IInnerSpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.InnerSpecs;
import com.bh.mall.dto.req.XN627905Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 内购产品规格-分页查询
 * @author: LENOVO 
 * @since: 2018年8月2日 下午4:30:04 
 * @history:
 */
public class XN627905 extends AProcessor {

    private IInnerSpecsAO innerSpecsAO = SpringContextHolder
        .getBean(IInnerSpecsAO.class);

    private XN627905Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        InnerSpecs condition = new InnerSpecs();

        condition.setInnerProductCode(req.getInnerProductCode());
        condition.setName(req.getName());
        // 类型转化错误了吗？？？放开报系统异常：null
        // condition.setPrice(Integer.valueOf(req.getPrice()));
        condition.setPrice(StringValidater.toInteger(req.getPrice()));
        condition.setIsSingle(req.getIsSingle());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IInnerSpecsAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return innerSpecsAO.queryInnerSpecsPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627905Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        ObjValidater.validateReq(req);
    }
}
