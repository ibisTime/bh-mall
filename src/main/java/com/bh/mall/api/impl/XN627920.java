
package com.bh.mall.api.impl;

import com.bh.mall.ao.ISpecsAO;
import com.bh.mall.api.AProcessor;
import com.bh.mall.common.JsonUtil;
import com.bh.mall.core.ObjValidater;
import com.bh.mall.core.StringValidater;
import com.bh.mall.dto.req.XN627920Req;
import com.bh.mall.dto.res.BooleanRes;
import com.bh.mall.exception.BizException;
import com.bh.mall.exception.ParaException;
import com.bh.mall.spring.SpringContextHolder;

/**
 * 规格库存
 * @author: nyc 
 * @since: 2018年3月25日 下午3:37:49 
 * @history:
 */
public class XN627920 extends AProcessor {

    private ISpecsAO specsAO = SpringContextHolder.getBean(ISpecsAO.class);

    private XN627920Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        specsAO.editRepertory(req.getCode(), req.getType(),
            StringValidater.toInteger(req.getNumber()), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN627920Req.class);
        ObjValidater.validateReq(req);
    }

}
