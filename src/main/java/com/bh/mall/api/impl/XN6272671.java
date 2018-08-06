package com.bh.mall.api.impl;

import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN6272671Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 代理申请升级接口
 * @author: clockorange 
 * @since: Jul 16, 2018 3:28:36 PM 
 * @history:
 */
public class XN6272671 extends AProcessor {

    private ISjFormAO userAO = SpringContextHolder.getBean(ISjFormAO.class);

    private XN6272671Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN6272671Req.class);
        ObjValidater.validateReq(req);
    }
}
