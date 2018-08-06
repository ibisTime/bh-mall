package com.bh.mall.api.impl;

import com.bh.mall.ao.IYxFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627267Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 分页查询意向代理
 * @author: nyc 
 * @since: 2018年8月5日 下午10:07:46 
 * @history:
 */
public class XN627267 extends AProcessor {

    private IYxFormAO yxFormAO = SpringContextHolder.getBean(IYxFormAO.class);

    private XN627267Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return yxFormAO.getYxForm(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627267Req.class);
        ObjValidater.validateReq(req);
    }

}
