package com.bh.mall.api.impl;

import com.bh.mall.ao.IChangeProductAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.StringValidater;
import com.bh.mall.domain.ChangeProduct;
import com.bh.mall.dto.req.XN627802Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *列表查询云仓申请单
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627802 extends AProcessor {
    private IChangeProductAO changeProductAO = SpringContextHolder
        .getBean(IChangeProductAO.class);

    private XN627802Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ChangeProduct condition = new ChangeProduct();
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setKeyword(req.getKeyword());

        return changeProductAO.queryChangeProductList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627802Req.class);

    }
}
