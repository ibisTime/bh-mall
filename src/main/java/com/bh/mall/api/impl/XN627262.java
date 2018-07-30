
package com.bh.mall.api.impl;

import com.bh.mall.ao.ISjFormAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.dto.req.XN627262Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 升级申请
 * @author: nyc 
 * @since: 2018年4月1日 上午10:58:40 
 * @history:
 */
public class XN627262 extends AProcessor {

    private ISjFormAO sjFormAO = SpringContextHolder.getBean(ISjFormAO.class);

    private XN627262Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sjFormAO.applySjForm(req.getUserId(), req.getHighLevel(),
            req.getPayPdf(), req.getPadAmount());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627262Req.class);
        ObjValidater.validateReq(req);
    }

}
