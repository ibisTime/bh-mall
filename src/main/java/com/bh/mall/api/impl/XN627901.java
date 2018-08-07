package com.bh.mall.api.impl;

import com.bh.mall.ao.IInOrderAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627901Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 提交云仓订单（有购物车）
 * @author: LENOVO 
 * @since: 2018年8月1日 下午10:27:42 
 * @history:
 */
public class XN627901 extends AProcessor {
    private IInOrderAO inOrderAO = SpringContextHolder
        .getBean(IInOrderAO.class);

    private XN627901Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return inOrderAO.addInOrder(req.getCodeList(), req.getApplyUser(),
            req.getApplyNote());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627901Req.class);
        ObjValidater.validateReq(req);
    }
}
