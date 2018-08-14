package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IProductReportAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ProductReport;
import com.bh.mall.dto.req.XN627851Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 团队出货数量
 * @author: nyc 
 * @since: 2018年6月30日 下午7:09:17 
 * @history:
 */
public class XN627851 extends AProcessor {

    private IProductReportAO productReportAO = SpringContextHolder
        .getBean(IProductReportAO.class);

    private XN627851Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProductReport condition = new ProductReport();
        condition.setTeamName(req.getTeamName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProductReportAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return productReportAO.queryProductReportPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627851Req.class);
        ObjValidater.validateReq(req);
    }

}
