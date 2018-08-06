package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627327Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 详情查询意向单
 * 
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627327 extends AProcessor {

    private IYxFormAO yxFormAO = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627327Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return yxFormAO.getYxForm(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627327Req.class);
        ObjValidater.validateReq(req);
    }

}
