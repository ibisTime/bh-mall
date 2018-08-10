package com.bh.mall.api.impl;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627900Req;
import com.bh.mall.dto.res.PKCodeRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 云仓订单下单（无购物车）
 * @author: LENOVO 
 * @since: 2018年8月1日 下午3:56:45 
 * @history:
 */
public class XN627900 extends AProcessor {

    private IInOrderAO inOrderAO = SpringContextHolder
        .getBean(IInOrderAO.class);

    private XN627900Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(inOrderAO.addInOrderNoCart(req.getApplyUser(),
            req.getSpecsCode(), StringValidater.toInteger(req.getQuantity()),
            req.getApplyNote()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627900Req.class);
        ObjValidater.validateReq(req);
    }

}
