package com.bh.mall.api.impl;

import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627307Req;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 列表查询升级单
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */

public class XN627307 extends AProcessor {
    private ISjFormAO sjFormAO = SpringContextHolder.getBean(ISjFormAO.class);

    private XN627307Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return sjFormAO.getSjForm(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627307Req.class);
        ObjValidater.validateReq(req);
    }

}