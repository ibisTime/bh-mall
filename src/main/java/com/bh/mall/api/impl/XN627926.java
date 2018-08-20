
package com.bh.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.Specs;
import com.bh.mall.dto.req.XN627926Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 规格库存
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627926 extends AProcessor {

    private ISpecsAO specsAO = SpringContextHolder.getBean(ISpecsAO.class);

    private XN627926Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Specs condition = new Specs();
        condition.setProductCode(req.getCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IInOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return specsAO.querySpecsList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627926Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
