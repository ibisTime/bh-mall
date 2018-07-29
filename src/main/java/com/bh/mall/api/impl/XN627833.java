package com.bh.mall.api.impl;

import com.bh.mall.ao.IWareLogAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627833Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 *详情
 * @author: nyc 
 * @since: 2018年4月10日 下午8:34:30 
 * @history:
 */
public class XN627833 extends AProcessor {
    private IWareLogAO wareLogAO = SpringContextHolder
        .getBean(IWareLogAO.class);

    private XN627833Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return wareLogAO.getWareLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627833Req.class);
        ObjValidater.validateReq(req);

    }
}
