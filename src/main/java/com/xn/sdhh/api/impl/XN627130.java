package com.xn.sdhh.api.impl;

import com.xn.sdhh.ao.IBusinessAO;
import com.xn.sdhh.api.AProcessor;
import com.xn.sdhh.common.JsonUtil;
import com.xn.sdhh.core.ObjValidater;
import com.xn.sdhh.dto.req.XN627130Req;
import com.xn.sdhh.exception.BizException;
import com.xn.sdhh.exception.ParaException;
import com.xn.sdhh.spring.SpringContextHolder;

/**
 * 录入业务
 * @author: nyc 
 * @since: 2018年7月29日 下午5:16:47 
 * @history:
 */
public class XN627130 extends AProcessor {

    private IBusinessAO businessAO = SpringContextHolder
        .getBean(IBusinessAO.class);

    private XN627130Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return businessAO.addBusiness(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627130Req.class);
        ObjValidater.validateReq(req);
    }

}
